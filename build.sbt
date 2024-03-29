ThisBuild / scalaVersion := "3.2.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "uno",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4",
    libraryDependencies += "org.apache.commons" % "commons-io" % "1.3.2",
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
    jacocoExcludes:=Seq("de.htwg.se.uno.view.*","de.htwg.se.uno.uno*","de.htwg.se.uno.main*","de.htwg.se.uno.util*"),
    jacocoCoverallsServiceName := "github-actions", 
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN")
                
   
  )
  .enablePlugins(JacocoCoverallsPlugin)
  

  scalacOptions ++= Seq(
      "-encoding", "utf8"
      )
  run / javaOptions ++= Seq(
      "-Dfile.encoding=UTF-8",
)


                  
//coverageEnabled := true

