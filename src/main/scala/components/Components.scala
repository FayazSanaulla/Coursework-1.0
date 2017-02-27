package components

import math.Math._
import stages.{ChartStage, TableStage}
import utils.Implicits._
import utils.Piezomodule._
import utils.RichDouble.DoubleExpansion
import utils.{Choices, Piezoceramic, Quartz, Titan}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import scalafx.scene.control._
import scalafx.scene.image.ImageView
import scalafx.scene.layout.{Pane, VBox}
/**
  * Created by faiaz on 18.12.16.
  */

object Components {

  type Pair = (Double, Double)

  val quartzTable: ObservableBuffer[Quartz] = ObservableBuffer[Quartz]()
  val piezoceramicTable: ObservableBuffer[Piezoceramic] = ObservableBuffer[Piezoceramic]()
  val titanTable: ObservableBuffer[Titan] = ObservableBuffer[Titan]()

  val quartzChart: ArrayBuffer[(Double, Double)] = mutable.ArrayBuffer[Pair]()
  val piezoceramicChart: ArrayBuffer[(Double, Double)] = mutable.ArrayBuffer[Pair]()
  val titanChart: ArrayBuffer[(Double, Double)] = mutable.ArrayBuffer[Pair]()

//  /**
//    * Pressure slider
//    */
//  val pressure = new Slider(0, 50, 0) {
//    minorTickCount = 4
//    showTickLabels = true
//    showTickMarks = true
//    orientation = Orientation.Vertical
//  }
//
//  pressure.value.onChange((_, _, newValue) => {
//    if (slider.value() == 0.0) resultTextFiled.setText("0.00")
//    else {
//      resultTextFiled.setText((
//        getPressure(newValue.doubleValue) *
//          soundPower(slider.value()) *
//          getCoefficient(Choices.withName(choiceBox.selectionModel().selectedItem.get())) *
//          getTemperature(temperature.value()))
//        .toString.roundingString)
//
//    }
//  })

//  /**
//    * Temperature slider
//    */
//  val temperature = new Slider(0, 100, 0) {
//    minorTickCount = 4
//    showTickLabels = true
//    showTickMarks = true
//    orientation = Orientation.Vertical
//  }
//
//  temperature.value.onChange((_, _, newValue) => {
//    if (slider.value() == 0.0) resultTextFiled.setText("0.00")
//    else {
//      resultTextFiled.setText((
//        getTemperature(newValue.doubleValue) *
//          soundPower(slider.value()) *
//          getCoefficient(Choices.withName(choiceBox.selectionModel().selectedItem.get())) *
//          getPressure(pressure.value()))
//        .toString.roundingString)
//    }
//  })

  /**
    * Power slider
    */
  val slider = new Slider(0, 100, 0) {
    minWidth = 400.0
    minorTickCount = 4
    showTickLabels = true
    showTickMarks = true
  }

  slider.value.onChange((_, _, newValue) => {
    val module = piezimodule(Choices.withName(choiceBox.selectionModel().selectedItem.get()))

    val inputValue = newValue.doubleValue
    val outputValue = voltage(module, inputValue)

    val forTable = outputValue
    val pair = (inputValue, outputValue)

    module match {
      case `quartzModule` =>
        quartzTable += new Quartz(inputValue, forTable)
        quartzChart += pair
      case `titanModule` =>
        titanTable += new Titan(inputValue, forTable)
        titanChart += pair
      case `piezoceramicModule` =>
        piezoceramicTable += new Piezoceramic(inputValue, forTable)
        piezoceramicChart += pair
    }

    resultTextFiled.setText(outputValue.roundDouble)
    sliderTextFiled.setText(inputValue.roundDouble)
  })

  val sliderTextFiled = new TextField {
    editable = false
    maxWidth = 70
    text = "0.00"
  }

  val resultTextFiled = new TextField {
    editable = false
    maxWidth = 100
    text = "0.00"
  }

  val choiceBox = new ChoiceBox[String] {
    maxWidth = 75
    maxHeight = 30
    items = ObservableBuffer(Choices.QUARTZ, Choices.TITAN, Choices.PIEZOCARAMIC)
    selectionModel().selectFirst()
    selectionModel().selectedItem.onChange((_, _, _) => slider.value = 0.0)
  }

  val choiceBoxPanel = new Pane {
    children = Seq(choiceBox)
  }

  val vbox = new VBox {
    spacing = 5
    children = Seq(
      choiceBox,
//      new HBox {
//        padding = Insets(20)
//        spacing = 5
//        children = Seq(
//          new VBox {
//            children = Seq(label("Температура"), temperature)
//          },
//          new VBox {
//            children = Seq(label("Тиск"), pressure)
//          })
//      },
      new Button("Таблиця") {
        onAction = handle(new TableStage().showAndWait())
      },
      new Button("Графік") {
        onAction = handle(new ChartStage().showAndWait())
      },
      new Button("Оновити") {
        onAction = handle({
          quartzTable.clear()
          piezoceramicTable.clear()
          titanTable.clear()
          quartzChart.clear()
          piezoceramicChart.clear()
          titanChart.clear()
        })
      })
  }

  val imageView = new ImageView("image.png")

  def label(text: String) = new Label(text)

  private def piezimodule(choice: Choices.Value): Double = choice match {
    case Choices.QUARTZ => quartzModule
    case Choices.PIEZOCARAMIC => piezoceramicModule
    case Choices.TITAN => titanModule
  }

//  private def getTemperature(value: Double): Double = value match {
//    case value: Double if value >= 0.0 && value <= 70.0 => 1.0
//    case value: Double if value > 70.0 && value <= 75.0 => 0.95
//    case value: Double if value > 75.0 && value <= 80.0 => 0.9
//    case value: Double if value > 80.0 && value <= 85.0 => 0.85
//    case value: Double if value > 85.0 && value <= 90.0 => 0.8
//    case _ => 0.7
//  }
//
//  private def getPressure(value: Double): Double = value match {
//    case value: Double if value >= 0.0 && value < 30.0 => 1.0
//    case value: Double if value >= 30.0 && value < 35.0 => 0.9
//    case value: Double if value >= 35.0 && value < 40.0 => 0.8
//    case value: Double if value >= 45.0 && value <= 50.0 => 0.7
//    case _ => 0.7
//  }
}
