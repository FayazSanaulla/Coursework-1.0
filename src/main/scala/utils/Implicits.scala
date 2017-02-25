package utils

import scala.language.implicitConversions

/**
  * Created by faiaz on 18.12.16.
  */
object Implicits {
  implicit def enumChoices2String(choice: Choices.Value): String = choice.toString
  implicit def double2int(double: Double): Int = double.toInt
  implicit def double2string(double: Double): String = double.toString
}
