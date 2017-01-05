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
  val TOURMALINE = Value("Турмалін")
}

object ChoicesValue {
  val quartzValue: Double = 2.3 * pow(10, -12)
  val titanValue: Double = 100 * pow(10, -12)
  val tourmalineValue = 1.5
}

class Quartz(input: Double, output: Double) {
  val inputVal = new StringProperty(this, "input" , input.toString)
  val outputVal = new StringProperty(this, "output", output.toString)
}
class Titan(input: Double, output: Double) {
  val inputVal = new StringProperty(this, "input" , input.toString)
  val outputVal = new StringProperty(this, "output", output.toString)
}

class Tourmaline(input: Double, output: Double) {
  val inputVal = new StringProperty(this, "input" , input.toString)
  val outputVal = new StringProperty(this, "output", output.toString)
}



