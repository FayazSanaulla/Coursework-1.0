package utils

import scalafx.beans.property.{DoubleProperty, StringProperty}

/**
  * Created by faiaz on 18.12.16.
  */
object Constants {

  implicit def double2DoubleProp(double: Double): DoubleProperty = DoubleProperty(double)
  implicit def double2String(double: Double): String = double.toString

  // VALUE
  final val Constant1 = 3.0
  final val Constant2 = 4.0
  final val Constant3 = 5.0

  //CHOICE VARIANT
  final val choice_1 = "Test1"
  final val choice_2 = "Test2"
  final val choice_3 = "Test3"
}
