package math

import scala.math._

/**
  * Created by faiaz on 05.01.17.
  */
object Math {

  val i0: Double = pow(10, -12)
  def soundPower(decibels: Int): Double = i0 * pow(10, decibels/10)
}
