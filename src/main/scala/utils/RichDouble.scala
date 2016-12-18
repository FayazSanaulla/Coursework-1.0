package utils

/**
  * Created by faiaz on 18.12.16.
  */
object RichDouble {
  implicit class DoubleExpansion(double: Double) {
    def roundAndReturnString: String = BigDecimal(double).setScale(2, BigDecimal.RoundingMode.HALF_UP).toString()
  }
}
