package utils

import javafx.scene.chart.XYChart.Data

import scalafx.scene.chart.XYChart

/**
  * Created by faiaz on 18.12.16.
  */
object Implicits {
  implicit def enumChoices2String(choice: Choices.Value): String = choice.toString
  implicit def double2int(double: Double): Int = double.toInt
  implicit def double2string(double: Double): String = double.toString
  implicit def toChartData(seq: Seq[(Double, Double)]): Seq[Data[Number, Number]] =  seq.map(xy => XYChart.Data[Number, Number](xy._1, xy._2))
}
