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
