package utils

import scala.language.implicitConversions
import scalafx.beans.property.DoubleProperty

/**
  * Created by faiaz on 18.12.16.
  */
object Constants {

  implicit def double2DoubleProp(double: Double): DoubleProperty = DoubleProperty(double)

  implicit def double2String(double: Double): String = double.toString

  implicit def doubleProp2double(doubleProp: DoubleProperty): Double = doubleProp()

  implicit def number2Double(number: Number): Double = number.doubleValue()

  implicit def number2String(number: Number): String = number.doubleValue().toString

  implicit def bigDec2Double(bigDecimal: BigDecimal): Double = bigDecimal.toDouble

  final val Constant1 = 3.0
  final val Constant2 = 4.0
  final val Constant3 = 5.0

  //CHOICE VARIANT
  final val choice_1 = "Test1"
  final val choice_2 = "Test2"
  final val choice_3 = "Test3"


}
