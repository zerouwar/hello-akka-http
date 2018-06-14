
name := "hello-akka-http"

version := "0.1"

scalaVersion := "2.12.6"

val akkaHttpV = "10.1.1"

val akkaV = "2.5.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaV,
  "com.typesafe.akka" %% "akka-stream" % akkaV,
  "com.typesafe.akka" %% "akka-http" % akkaHttpV,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
)

enablePlugins(JavaAppPackaging)