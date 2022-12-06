ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val helloworld = (project in file("./helloworld"))
  .settings(
    name := "tapir-hello-world",
    libraryDependencies ++= Dependencies.common ++ Dependencies.server
  )

lazy val workshop = (project in file("./workshop"))
  .settings(
    name := "tapir-workshop",
    libraryDependencies ++= Dependencies.common ++ Dependencies.server
  )

lazy val endpoints = (project in file("./endpoints"))
  .settings(
    name := "tapir-example-endpoints",
    libraryDependencies ++= Dependencies.common
  )

lazy val client = (project in file("./client"))
  .settings(
    name := "tapir-example-client",
    libraryDependencies ++= Dependencies.common ++ Dependencies.client
  ).dependsOn(endpoints)

lazy val server = (project in file("./server"))
  .settings(
    name := "tapir-example-server",
    libraryDependencies ++= Dependencies.common ++ Dependencies.server
  ).dependsOn(endpoints)
