import sbt._

object Dependencies {
  object Versions {
    val tapir = "1.2.3"
    val akka = "2.7.0"
    val akkaHttp = "10.4.0"
    val akkaHttpBackend = "3.8.3"
  }

  val common = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-core" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % Versions.tapir
  )

  val server = Seq(
    "com.typesafe.akka" %% "akka-actor-typed" % Versions.akka,
    "com.typesafe.akka" %% "akka-stream" % Versions.akka,
    "com.typesafe.akka" %% "akka-http" % Versions.akkaHttp,
    "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % Versions.tapir
  )

  val client = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-sttp-client" % Versions.tapir,
    "com.typesafe.akka" %% "akka-stream" % Versions.akka,
    "com.softwaremill.sttp.client3" %% "akka-http-backend" % Versions.akkaHttpBackend
  )
}
