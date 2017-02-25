package utils

/**
  * Created by faiaz on 18.12.16.
  */
object RichDouble {
  implicit class DoubleExpansion(double: Double) {
    def roundDouble: Double = BigDecimal(double).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }
}
