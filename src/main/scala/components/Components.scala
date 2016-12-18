package components

import utils.Constants._
import utils.Logger
import utils.RichDouble.DoubleExpansion

import scalafx.Includes._
import scalafx.beans.property.DoubleProperty
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.{ChoiceBox, Slider, TextField}
import scalafx.scene.image.{Image, ImageView}

/**
  * Created by faiaz on 18.12.16.
  */
object Components extends Logger {

  @volatile var coefficient = DoubleProperty(1)

  val slider = new Slider(0, 100, 0) {
    minWidth = 400.0
    minorTickCount = 4
    showTickLabels = true
    showTickMarks = true
  }

  slider.value.onChange((_, _, newValue) => {
    resultTextFiled.setText((newValue.doubleValue * coefficient()).roundAndReturnString)
    sliderTextFiled.setText(newValue.doubleValue.roundAndReturnString)
  })

  val sliderTextFiled = new TextField {
    editable = false
  }

  val resultTextFiled = new TextField {
    editable = false
  }

  val choiceBox = new ChoiceBox[String] {
    maxWidth = 80
    maxHeight = 50
    items = ObservableBuffer(choice_1, choice_2, choice_3)
    selectionModel().selectFirst()
    selectionModel().selectedItem.onChange(
      (_, _, newValue) => newValue match {
        case `choice_1` =>
          log.info(s"$choice_1 is called, coefficient = ${Constant1()}")
          coefficient = Constant1
          resultTextFiled.setText((coefficient() * slider.value()).roundAndReturnString)
        case `choice_2` =>
          log.info(s"$choice_2 is called, coefficient = ${Constant2()}")
          coefficient = Constant2
          resultTextFiled.setText((coefficient() * slider.value()).roundAndReturnString)
        case `choice_3` =>
          log.info(s"$choice_3 is called, coefficient = ${Constant3()}")
          coefficient = Constant3
          resultTextFiled.setText((coefficient() * slider.value()).roundAndReturnString)
      }
    )
  }

  val imageView = new ImageView {
    // image = new Image(getClass.getResource("epsya.jpg").toExternalForm)
    // image = new Image("file:image.png")
    image = new Image("http://www.scala-lang.org/resources/img/scala-logo.png")
  }

}
