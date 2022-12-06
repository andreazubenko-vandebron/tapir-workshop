package nl.vandebron

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter

import scala.concurrent.Future
import scala.io.StdIn

object Server extends App {

  implicit val system = ActorSystem(Behaviors.empty, "my-system")
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.executionContext

  val serverEndpoints: List[ServerEndpoint[Any, Future]] = List(
    Endpoints.getAllCats.serverLogicSuccess(Logic.getAllCats),
    Endpoints.getCat.serverLogicSuccess(Logic.getCat),
    Endpoints.postCat.serverLogicSuccess(Logic.createCat)
  )

  val docs = SwaggerInterpreter().fromEndpoints[Future](
    List(
      Endpoints.getCat,
      Endpoints.getAllCats,
      Endpoints.postCat
    ),
    "Tapir&Cats",
    "1.0"
  )

  val routes = AkkaHttpServerInterpreter().toRoute(serverEndpoints ++ docs)

  // starting the server
  val bindingFuture = Http()
    .newServerAt("localhost", 8080)
    .bind(routes)

  println(s"Server now online. Please navigate to http://localhost:8080/docs\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}
