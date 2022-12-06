package nl.vandebron

import sttp.tapir._
import sttp.tapir.EndpointIO.annotations.{endpointInput, path, query => queryA}

object Endpoints {
  val hello: PublicEndpoint[String, Unit, String, Any] =
    endpoint
      .get
      .in("hello")
      .in(query[String]("name"))
      .out(stringBody)


  // Alternative: using annotations
  @endpointInput("annotated/{name}")
  final case class HelloInput(
                   @path
                   name: String,
                   @queryA
                   age: Int
  )

  val helloAnnotations: PublicEndpoint[HelloInput, Unit, String, Any] = endpoint
    .get
    .in("hello")
    .in(EndpointInput.derived[HelloInput])
    .out(stringBody)
}
