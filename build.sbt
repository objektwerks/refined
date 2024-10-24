name := "refined"
version := "0.1-SNAPSHOT"
scalaVersion := "3.6.1"
organization := "objektwerks"
libraryDependencies ++= {
  Seq(
    "eu.timepit" %% "refined" % "0.11.2",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
