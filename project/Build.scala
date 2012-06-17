import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "playApp"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
      "org.docx4j" % "docx4j" % "2.8.0"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here
        resolvers += "webjars" at "http://webjars.github.com/m2"
    )

}
