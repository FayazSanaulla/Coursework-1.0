package components

import utils.Choices
import utils.Implicits._
import utils.RichDouble.DoubleExpansion

import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import scalafx.geometry.{Insets, Orientation}
import scalafx.scene.control.{ChoiceBox, Label, Slider, TextField}
import scalafx.scene.image.ImageView
import scalafx.scene.layout.{HBox, Pane, VBox}
/**
  * Created by faiaz on 18.12.16.
  */

object Components {

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
        getTemperature(pressure.value())).roundAndReturnString)
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
    resultTextFiled.setText(
      (newValue.doubleValue *
        getCoefficient(Choices.withName(choiceBox.selectionModel().selectedItem.get())) *
        getPressure(pressure.value()) *
        getTemperature(temperature.value())).roundAndReturnString)
    sliderTextFiled.setText(newValue.doubleValue.roundAndReturnString)
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
    items = ObservableBuffer(Choices.QUARTZ, Choices.SILICON, Choices.TOURMALINE)
    selectionModel().selectFirst()
    selectionModel().selectedItem.onChange(
      (_, _, newValue) => Choices.withName(newValue) match {
        case Choices.QUARTZ =>
          val quartz = Choices.QUARTZ
          resultTextFiled.setText((getCoefficient(quartz) * slider.value()).roundAndReturnString)
        case Choices.SILICON =>
          val silicon = Choices.SILICON
          resultTextFiled.setText((getCoefficient(silicon) * slider.value()).roundAndReturnString)
        case Choices.TOURMALINE =>
          val tourmaline = Choices.TOURMALINE
          resultTextFiled.setText((getCoefficient(tourmaline) * slider.value()).roundAndReturnString)
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
        spacing = 0
        children = Seq(temperature, pressure)
      })
  }

  val imageView = new ImageView("image.png")

  def label(text: String) = new Label(text)

  private def getCoefficient(choice: Choices.Value): Double = choice match {
    case Choices.QUARTZ => 2.0
    case Choices.TOURMALINE => 3.0
    case Choices.SILICON => 4.0
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
    case value: Double if value >= 0.0 && value <= 30.0 => 1.0
    case _ => 0.7
  }
}
