import sbt.{url, *}
import sbt.Keys.*

import scala.concurrent.duration.*

resolvers += Resolver.mavenLocal

val Version = "0.0.3"

ThisBuild / organization := "com.fastscala"
ThisBuild / version := Version
ThisBuild / scalaVersion := "3.6.3"

lazy val fs_demo = (project in file("fs-demo"))
  .enablePlugins(JavaServerAppPackaging, SystemdPlugin)
  .settings(
    name := "fs-demo",
    organization := "com.fastscala",

    Compile / packageBin / mainClass := Some("com.fastscala.demo.server.JettyServer"),
    Compile / mainClass := Some("com.fastscala.demo.server.JettyServer"),

    Compile / unmanagedResourceDirectories += baseDirectory.value / "src" / "main" / "scala",

    publishArtifact := true,

    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.5.7",
      "at.favre.lib" % "bcrypt" % "0.10.2",
      "com.lihaoyi" %% "scalatags" % "0.13.1",
      "com.fastscala" %% "fs-circe" % "0.0.13",
      "com.fastscala" %% "fs-db" % "0.0.13",
      "com.fastscala" %% "fs-core" % "0.0.13",
      "com.fastscala" %% "fs-scala-xml" % "0.0.13",
      "com.fastscala" %% "fs-components" % "0.0.13",
    ),

    bashScriptEnvConfigLocation := Some("/etc/default/" + (Linux / packageName).value),
    rpmRelease := Version,
    rpmVendor := "kezlisolutions",
    rpmLicense := Some("none"),

    Linux / daemonUser := "fs_demo",
    Linux / daemonGroup := "fs_demo",

    javaOptions += "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005",
    Compile / run / fork := true,
    Compile / run / connectInput := true,
    javaOptions += "-Xmx2G",
    javaOptions += "-Xms400M",
  )
