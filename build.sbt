name := "refined"
version := "0.1-SNAPSHOT"
scalaVersion := "3.4.2-RC1"
organization := "objektwerks"
libraryDependencies ++= {
  Seq(
    "eu.timepit" %% "refined" % "0.11.1",
    "org.scalatest" %% "scalatest" % "3.2.18" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
