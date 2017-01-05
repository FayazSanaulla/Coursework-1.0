package stages

import components.Components
import utils.RichDouble.DoubleExpansion

import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.chart.{LineChart, NumberAxis, XYChart}
import scalafx.stage.Stage

/**
  * Created by faiaz on 03.01.17.
  */
class ChartStage extends Stage {

  val quartz = "Кварц"
  val titan = "Титан"
  val tourmaline = "Турмалін"
  resizable = false

      title = s"Графік(" +
        s"t: ${Components.temperature.value().roundAndReturnString}," +
        s" p: ${Components.pressure.value().roundAndReturnString})"
      scene = new Scene {
        root = {

          val xAxis = NumberAxis("Сила звуку", 0, 150, 10)
          val yAxis = NumberAxis("Заряд", 0, 150, 10)

          val toChartData = (xy: (Double, Double)) => XYChart.Data[Number, Number](xy._1, xy._2)

          val series1 = new XYChart.Series[Number, Number] {
            name = quartz
            data = getData(quartz).map(toChartData)
          }

          val series2 = new XYChart.Series[Number, Number] {
            name = tourmaline
            data = getData(tourmaline).map(toChartData)
          }

          val series3 = new XYChart.Series[Number, Number] {
            name = titan
            data = getData(titan).map(toChartData)
          }

          new LineChart[Number, Number](xAxis, yAxis, ObservableBuffer(series1, series2, series3))
        }
      }

  private def getData(str: String) = str match {
      case "Турмалін" => Components.tourmalineArr
      case "Кварц" => Components.quartzArr
      case "Титан" => Components.titanArr
  }
}
