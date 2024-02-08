name := "refined"
version := "0.1-SNAPSHOT"
scalaVersion := "3.4.0-RC4"
organization := "objektwerks"
libraryDependencies ++= {
  Seq(
    "eu.timepit" %% "refined" % "0.11.1",
    "org.scalatest" %% "scalatest" % "3.2.17" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
