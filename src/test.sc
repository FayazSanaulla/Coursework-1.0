import utils.RichDouble.DoubleExpansion

import scala.math.pow
val double = (1231 * pow(10, -13)).toString
val index = double.indexOf("E")
val arr = double.split("E")
(arr(0).toDouble.roundAndReturnString+"E"+arr(1))
