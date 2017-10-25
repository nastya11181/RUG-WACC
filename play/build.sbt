import NativePackagerKeys._

name := "eventual"

version := "1.0-SNAPSHOT"

maintainer:= "Your Name"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)

play.Project.playScalaSettings
