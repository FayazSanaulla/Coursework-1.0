name := "coursework_sanaulla"
version := "1.0"
scalaVersion := "2.11.8"

packageName in Docker := "course_work"
version in Docker := "latest"
//dockerRepository := Some("faiaz")

enablePlugins(JavaAppPackaging)

libraryDependencies ++= Seq(
  "org.scalafx" % "scalafx_2.11" % "8.0.102-R11"
)
    