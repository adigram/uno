ThisBuild / scalaVersion := "3.2.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "uno",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "com.google.inject" % "guice" % "5.1.0",
    libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.2").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.1.0",
    libraryDependencies += ("com.typesafe.play" %% "play-json" % "2.10.0-RC5"),
    libraryDependencies += ("org.scala-lang.modules" %% "scala-swing" % "3.0.0").cross(CrossVersion.for3Use2_13),
    jacocoReportSettings := JacocoReportSettings(
      "Jacoco Coverage Report",
      None,
      JacocoThresholds(),
      Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML), // note XML formatter
      "utf-8"
    ),
    jacocoExcludes:=Seq("*view.*","*Main*", "*UnoModule*", "*Observer*")
                
   
  )
  .enablePlugins(JacocoCoverallsPlugin)
  

  scalacOptions ++= Seq(
      "-encoding", "utf8"
      )
  run / javaOptions ++= Seq(
      "-Dfile.encoding=UTF-8",
)


                   
//coverageEnabled := true

