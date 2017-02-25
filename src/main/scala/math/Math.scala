package math

import utils.Environment.capacitor

/**
  * Created by faiaz on 05.01.17.
  */
object Math {
  def voltage(piezomodule: Double, power: Double): Double = (piezomodule * power) / capacitor
}
