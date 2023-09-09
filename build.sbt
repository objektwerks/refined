name := "refined"
version := "0.1-SNAPSHOT"
scalaVersion := "3.3.1"
organization := "objektwerks"
libraryDependencies ++= {
  val jsoniterVersion = "2.23.4"
  Seq(
    "eu.timepit" %% "refined" % "0.11.0",
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % jsoniterVersion,
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % jsoniterVersion % "provided",
    "org.scalatest" %% "scalatest" % "3.2.16" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
