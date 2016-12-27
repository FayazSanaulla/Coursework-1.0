package components

import utils.Choices
import utils.Implicits._
import utils.RichDouble.DoubleExpansion

import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.{ChoiceBox, Label, Slider, TextField}
import scalafx.scene.image.ImageView
import scalafx.scene.layout.Pane
/**
  * Created by faiaz on 18.12.16.
  */

object Components {

  val slider = new Slider(0, 100, 0) {
    minWidth = 400.0
    minorTickCount = 4
    showTickLabels = true
    showTickMarks = true
  }

  slider.value.onChange((_, _, newValue) => {
    resultTextFiled.setText((newValue.doubleValue * getCoefficient(Choices.withName(choiceBox.selectionModel().selectedItem.get()))).roundAndReturnString)
    sliderTextFiled.setText(newValue.doubleValue.roundAndReturnString)
  })

  val sliderTextFiled = new TextField {
    editable = false
    maxWidth = 70
  }

  val resultTextFiled = new TextField {
    editable = false
    maxWidth = 70
  }

  val choiceBox = new ChoiceBox[String] {
    maxWidth = 80
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
    children = Seq(choiceBox)
  }

  val imageView = new ImageView("image.png")

  def label(text: String) = new Label(text)

  def getCoefficient(choice: Choices.Value): Double = choice match {
    case Choices.QUARTZ => 2.0
    case Choices.TOURMALINE => 3.0
    case Choices.SILICON => 4.0
    case _ => 1.0
  }
}
