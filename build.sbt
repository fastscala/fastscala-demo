import sbt.*
import sbt.Keys.*

resolvers += Resolver.mavenLocal

val Version = "0.0.3"

ThisBuild / organization := "com.fastscala"
ThisBuild / version := Version
ThisBuild / scalaVersion := "3.10.0"

val FastScalaVersion = "0.0.51"

// FastScala library dependencies
val FastScalaDeps = Seq(
  "com.fastscala" %% "fs-circe" % FastScalaVersion,
  "com.fastscala" %% "fs-db" % FastScalaVersion,
  "com.fastscala" %% "fs-core" % FastScalaVersion,
  "com.fastscala" %% "fs-scala-xml" % FastScalaVersion,
  "com.fastscala" %% "fs-components" % FastScalaVersion,
)

// ---------- Demo app ----------
lazy val fs_demo = (project in file("fs-demo"))
  .enablePlugins(JavaServerAppPackaging, SystemdPlugin)
  .settings(
    name := "fs-demo",
    version := "0.1.0",
    organization := "com.fastscala",
    scalacOptions ++= Seq("-old-syntax", "-rewrite"),
    scalacOptions ++= Seq("-rewrite", "-source", "3.7-migration"),
    //    scalacOptions ++= Seq("-explain"),

    Compile / packageBin / mainClass := Some("com.fastscala.demo.server.JettyServer"),
    Compile / mainClass := Some("com.fastscala.demo.server.JettyServer"),
    Compile / unmanagedResourceDirectories += baseDirectory.value / "src" / "main" / "scala",
    publishArtifact := true,
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.7.0",
      "at.favre.lib" % "bcrypt" % "0.10.2",
      "com.lihaoyi" %% "scalatags" % "0.13.1",
      "commons-io" % "commons-io" % "2.21.0",
    ),
    bashScriptEnvConfigLocation := Some("/etc/default/" + (Linux / packageName).value),
    rpmRelease := "1.0.0",
    rpmVendor := "kezlisolutions",
    rpmLicense := Some("none"),
    Linux / daemonUser := "fs_demo",
    Linux / daemonGroup := "fs_demo",
    javaOptions += "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005",
    Compile / run / fork := true,
    Compile / run / connectInput := true,
    javaOptions += "-Xmx2G",
    javaOptions += "-Xms400M"
  )
  .settings(libraryDependencies ++= FastScalaDeps)
// ---------- end of Demo app ----------
