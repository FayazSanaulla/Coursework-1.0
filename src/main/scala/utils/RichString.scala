package utils

import utils.RichDouble.DoubleExpansion

/**
  * Created by faiaz on 05.01.17.
  */
object RichString {
  implicit class StringExpansion(string: String) {
    def roundStringDouble: String = {
      val sep = "E"
      val arr = string.split(sep)
      arr(0).toDouble.roundAndReturnString(5) + sep + arr(1)
    }
  }
}
