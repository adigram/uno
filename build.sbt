ThisBuild / scalaVersion := "3.2.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "uno",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )

scalacOptions ++= Seq(
    "-encoding", "utf8"
    )
run / javaOptions ++= Seq(
    "-Dfile.encoding=UTF-8",
)
coverageEnabled := true
