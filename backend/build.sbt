name := """mongo"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies += guice

libraryDependencies ++= Seq(
    "org.mongodb" % "mongodb-driver" % "3.5.0"
)

libraryDependencies ++= Seq(
    "org.mongodb" % "mongodb-driver-reactivestreams" % "1.6.0"
)

libraryDependencies ++= Seq(
    "org.mongodb" % "mongodb-driver-rx" % "1.5.0"
)
