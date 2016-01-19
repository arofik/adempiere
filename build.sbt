/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
  * This program is free software; you can redistribute it and/or modify it    *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope   *
  * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
  * See the GNU General Public License for more details.                       *
  * You should have received a copy of the GNU General Public License along    *
  * with this program; if not, write to the Free Software Foundation, Inc.,    *
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
  * For the text or an alternative of this public license, you may reach us    *
  * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
  * Contributor(s): Victor Perez www.e-evolution.com                           *
  * ****************************************************************************/

/*
* Builder Test infrastructure
* eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 06/01/16.
*/

import _root_.sbtassembly.AssemblyPlugin.autoImport._

name := "adempierePOS"
version := "1.0.0"


scalaVersion := "2.11.7"

fork := true
val adempiereProperties = "-DPropertyFile=/Users/e-Evolution/AdempiereTest.properties"
//scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding" , "utf8")
javaOptions in Test := Seq (adempiereProperties)


lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "org.adempiere.pos",
  scalaVersion := "2.11.7"
)

val sourceAdempiere = "/Users/e-Evolution/Documents/Develop/ADempiere/adempiere"

unmanagedSourceDirectories in Compile <++= baseDirectory { base =>
  Seq(
    base / "org.adempiere.pos"  / "src" / "main" / "java" / "base",
    base / "org.adempiere.pos"  / "src" / "main" / "java" / "ui" / "swing" ,
    base / "org.adempiere.pos"  / "src" / "main" / "java" / "ui" / "zk"
  )
}

//javaSource in Test := baseDirectory.value / "org.adempiere.pos"  / "src" / "test" / "java"

scalaSource in Compile := baseDirectory.value / "org.adempiere.pos" / "src" / "main" / "scala"
scalaSource in Test := baseDirectory.value / "org.adempiere.pos" / "src" / "test" / "scala"

val additionalClasses = file(sourceAdempiere + "/zkwebui/WEB-INF/classes")

unmanagedClasspath in Compile += additionalClasses
unmanagedClasspath in Runtime += additionalClasses

unmanagedJars in Compile ++= (file(sourceAdempiere + "/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/target/scala-2.11") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/zkwebui/WEB-INF/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/tools/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/packages") * "*.jar").classpath


assemblyJarName in assembly := "ADempierePOS.jar"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = true, includeDependency = false)

lazy val adempierePOS = (project in file(".")).
  settings(commonSettings: _*).
  settings(
  )


resolvers ++= Seq(
  "Spray repository" at "http://repo.spray.io",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Scaladin Snapshots" at "http://henrikerola.github.io/repository/snapshots/"
)

libraryDependencies ++= Seq(
  "com.vaadin" % "vaadin-server" % "7.5.6",
  "com.vaadin" % "vaadin-client-compiled" % "7.5.6",
  "com.vaadin" % "vaadin-themes" % "7.5.6",
  "vaadin.scala" %% "scaladin" % "3.1-SNAPSHOT",
  "org.scalatest" % "scalatest_2.11" % "2.2.4"
)
