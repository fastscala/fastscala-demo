import sbt.*
import sbt.Keys.*

resolvers += Resolver.mavenLocal

val Version = "0.0.3"

ThisBuild / organization := "com.fastscala"
ThisBuild / version := Version
ThisBuild / scalaVersion := "3.8.3"

val FastScalaVersion = "0.0.51"

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
      "org.typelevel" %% "cats-effect" % "3.7.0",
      "at.favre.lib" % "bcrypt" % "0.10.2",
      "com.lihaoyi" %% "scalatags" % "0.13.1",
      "commons-io" % "commons-io" % "2.21.0",
      "com.fastscala" %% "fs-circe" % FastScalaVersion,
      "com.fastscala" %% "fs-db" % FastScalaVersion,
      "com.fastscala" %% "fs-core" % FastScalaVersion,
      "com.fastscala" %% "fs-scala-xml" % FastScalaVersion,
      "com.fastscala" %% "fs-components" % FastScalaVersion,
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
