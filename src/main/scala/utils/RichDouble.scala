package utils

/**
  * Created by faiaz on 18.12.16.
  */
object RichDouble {
  implicit class DoubleExpansion(double: Double) {
    def roundAndReturnString(scale: Int = 2): String = BigDecimal(double).setScale(scale, BigDecimal.RoundingMode.HALF_UP).toString()
  }
}
