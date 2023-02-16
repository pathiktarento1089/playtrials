name := """task4"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.10"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.32"
libraryDependencies += guice