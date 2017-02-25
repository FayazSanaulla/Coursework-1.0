package utils

import scala.math.pow
import scalafx.beans.property.StringProperty

/**
  * Created by faiaz on 27.12.16.
  */
object Choices extends Enumeration {
  type Choice = Value
  val TITAN = Value("Титанат Барію")
  val QUARTZ = Value("Кварц")
  val PIEZOCARAMIC = Value("Пєзокераміка")
}

object Piezomodule {
  final val quartzModule: Double = 2.3 * pow(10, -12)
  final val titanModule: Double = 100 * pow(10, -12)
  final val piezoceramicModule: Double = pow(10, -10)
}

object Environment {
  final val capacitor: Double = 2.4 * pow(10, -9)
}

class Quartz(input: Double, output: Double) {
  val inputVal = new StringProperty(this, "input" , input.toString)
  val outputVal = new StringProperty(this, "output", output.toString)
}
class Titan(input: Double, output: Double) {
  val inputVal = new StringProperty(this, "input" , input.toString)
  val outputVal = new StringProperty(this, "output", output.toString)
}

class Piezoceramic(input: Double, output: Double) {
  val inputVal = new StringProperty(this, "input" , input.toString)
  val outputVal = new StringProperty(this, "output", output.toString)
}



