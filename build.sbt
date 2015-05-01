name := "di-test"

version := "1.0"

scalaVersion := "2.10.5"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.2.1",
  "org.flywaydb" % "flyway-core" % "3.2.1",
  "org.scalatest" % "scalatest_2.10" % "2.2.4" % "test",
  "org.mockito" % "mockito-all" % "1.10.8" % "test"
)
