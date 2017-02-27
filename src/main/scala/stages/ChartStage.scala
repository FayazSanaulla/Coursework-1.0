package stages

import components.Components
import utils.Choices
import utils.Implicits._

import scala.annotation.implicitNotFound
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.chart.{LineChart, NumberAxis, XYChart}
import scalafx.stage.Stage

/**
  * Created by faiaz on 27.02.17.
  */
class ChartStage extends Stage {

  private def getData(element: Choices.Value) = element match {
    case Choices.QUARTZ => Components.quartzChart
    case Choices.TITAN => Components.titanChart
    case Choices.PIEZOCARAMIC => Components.piezoceramicChart
  }

  scene = new Scene {
    root = {

      val xAxis = NumberAxis("Сила, H", 0, 100, 10)
      val yAxis = NumberAxis("Напруга, V", 0, 5, 1)

      @implicitNotFound("implicit conversion to Seq[Data] not found")
      val quartz = new XYChart.Series[Number, Number] {
        name = "Кварц"
        data = getData(Choices.QUARTZ)
      }

      @implicitNotFound("implicit conversion to Seq[Data] not found")
      val titan = new XYChart.Series[Number, Number] {
        name = "Титан"
        data = getData(Choices.TITAN)
      }
      @implicitNotFound("implicit conversion to Seq[Data] not found")
      val piezoceramic = new XYChart.Series[Number, Number] {
        name = "П'єзокераміка"
        data = getData(Choices.PIEZOCARAMIC)
      }

      new LineChart[Number, Number](xAxis, yAxis, ObservableBuffer(quartz, titan, piezoceramic))
    }
  }
}
