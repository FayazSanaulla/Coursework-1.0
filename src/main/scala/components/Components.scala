package components

import stages.ChartStage
import utils.Choices
import utils.ChoicesValue._
import utils.Implicits._
import utils.RichDouble.DoubleExpansion

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import scalafx.geometry.{Insets, Orientation}
import scalafx.scene.control._
import scalafx.scene.image.ImageView
import scalafx.scene.layout.{HBox, Pane, VBox}
/**
  * Created by faiaz on 18.12.16.
  */

object Components {

  type Pair = (Double, Double)

  val quartzArr: ArrayBuffer[(Double, Double)] = mutable.ArrayBuffer[Pair]()
  val tourmalineArr: ArrayBuffer[(Double, Double)] = mutable.ArrayBuffer[Pair]()
  val titanArr: ArrayBuffer[(Double, Double)] = mutable.ArrayBuffer[Pair]()


  /**
    * Pressure slider
    */
  val pressure = new Slider(0, 50, 0) {
    minorTickCount = 4
    showTickLabels = true
    showTickMarks = true
    orientation = Orientation.Vertical
  }

  pressure.value.onChange((_, _, newValue) => {
    resultTextFiled.setText((
        getPressure(newValue.doubleValue) *
        slider.value() *
        getCoefficient(Choices.withName(choiceBox.selectionModel().selectedItem.get())) *
        getTemperature(temperature.value())).roundAndReturnString)
  })

  /**
    * Temperature slider
    */
  val temperature = new Slider(0, 100, 0) {
    minorTickCount = 4
    showTickLabels = true
    showTickMarks = true
    orientation = Orientation.Vertical
  }

  temperature.value.onChange((_, _, newValue) => {

    resultTextFiled.setText((
        getTemperature(newValue.doubleValue) *
        slider.value() *
        getCoefficient(Choices.withName(choiceBox.selectionModel().selectedItem.get())) *
        getPressure(pressure.value())).roundAndReturnString)
  })

  /**
    * Main slider
    */
  val slider = new Slider(0, 100, 0) {
    minWidth = 400.0
    minorTickCount = 4
    showTickLabels = true
    showTickMarks = true
  }

  slider.value.onChange((_, _, newValue) => {
    val coef = getCoefficient(Choices.withName(choiceBox.selectionModel().selectedItem.get()))
    val inputValue = newValue.doubleValue
    val outputValue = newValue.doubleValue * coef * getPressure(pressure.value()) * getTemperature(temperature.value())
    val pair = (inputValue, outputValue)

    coef match {
      case `quartzValue` => quartzArr += pair
      case `titanValue` => titanArr += pair
      case `tourmalineValue` => tourmalineArr += pair
    }

    resultTextFiled.setText(outputValue.roundAndReturnString)
    sliderTextFiled.setText(inputValue.roundAndReturnString)
  })

  val sliderTextFiled = new TextField {
    editable = false
    maxWidth = 70
    text = "0.00"
  }

  val resultTextFiled = new TextField {
    editable = false
    maxWidth = 70
    text = "0.00"
  }

  val choiceBox = new ChoiceBox[String] {
    maxWidth = 100
    maxHeight = 30
    items = ObservableBuffer(Choices.QUARTZ, Choices.TITAN, Choices.TOURMALINE)
    selectionModel().selectFirst()
    selectionModel().selectedItem.onChange(
      (_, _, newValue) => Choices.withName(newValue) match {
        case Choices.QUARTZ =>
          val quartz = Choices.QUARTZ
          slider.value = 0.00
          resultTextFiled.setText((getCoefficient(quartz) * slider.value() * getTemperature(temperature.value()) * getPressure(pressure.value())).roundAndReturnString)
        case Choices.TITAN =>
          val silicon = Choices.TITAN
          slider.value = 0.00
          resultTextFiled.setText((getCoefficient(silicon) * slider.value() * getTemperature(temperature.value()) * getPressure(pressure.value())).roundAndReturnString)
        case Choices.TOURMALINE =>
          val tourmaline = Choices.TOURMALINE
          slider.value = 0.00
          resultTextFiled.setText((getCoefficient(tourmaline) * slider.value() * getTemperature(temperature.value()) * getPressure(pressure.value())).roundAndReturnString)
      }
    )
  }

  val choiceBoxPanel = new Pane {
    children = Seq(choiceBox, temperature, pressure)
  }

  val vbox = new VBox {
    children = Seq(
      choiceBox,
      new HBox {
        padding = Insets(20)
        spacing = 5
        children = Seq(
          new VBox {
            children = Seq(label("Температура"), temperature)
          },
          new VBox {
            children = Seq(label("Тиск"), pressure)
          })
      },
      new Button("Побудувати графік") {
        onAction = handle(ChartStage.showAndWait())
      })
  }

  val imageView = new ImageView("image.png")

  def label(text: String) = new Label(text)

  private def getCoefficient(choice: Choices.Value): Double = choice match {
    case Choices.QUARTZ => quartzValue
    case Choices.TOURMALINE => tourmalineValue
    case Choices.TITAN => titanValue
    case _ => 1.0
  }

  private def getTemperature(value: Double): Double = value match {
    case value: Double if value >= 0.0 && value <= 70.0 => 1.0
    case value: Double if value > 70.0 && value <= 75.0 => 0.95
    case value: Double if value > 75.0 && value <= 80.0 => 0.9
    case value: Double if value > 80.0 && value <= 85.0 => 0.85
    case value: Double if value > 85.0 && value <= 90.0 => 0.8
    case _ => 0.7
  }

  private def getPressure(value: Double): Double = value match {
    case value: Double if value >= 0.0 && value < 30.0 => 1.0
    case value: Double if value >= 30.0 && value < 35.0 => 0.9
    case value: Double if value >= 35.0 && value < 40.0 => 0.8
    case value: Double if value >= 45.0 && value <= 50.0 => 0.7
    case _ => 0.7
  }
}
