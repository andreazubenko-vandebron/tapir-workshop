package nl.vandebron

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter

import scala.concurrent.Future
import scala.io.StdIn

object Server extends App {

  implicit val system = ActorSystem(Behaviors.empty, "my-system")
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.executionContext

  val helloWorldServerEndpoint =
    Endpoints.hello.serverLogicSuccess(Logic.helloLogic)

  val helloWorldAnnotationServerEndpoint =
    Endpoints.helloAnnotations.serverLogicSuccess(helloInput => Future.successful(s"Hello ${helloInput.name}, you're ${helloInput.age}"))

  val docs = SwaggerInterpreter().fromEndpoints[Future](
    List(
      Endpoints.hello,
      Endpoints.helloAnnotations
    ),
    "Tapir Hello World",
    "1.0"
  )

  val routes = AkkaHttpServerInterpreter().toRoute(List(helloWorldServerEndpoint, helloWorldAnnotationServerEndpoint) ++ docs)

  // starting the server
  val bindingFuture = Http()
    .newServerAt("localhost", 8080)
    .bind(routes)

  println(s"Server now online. Please navigate to http://localhost:8080/hello\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}
