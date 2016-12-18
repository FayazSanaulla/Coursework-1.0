package components

import java.text.NumberFormat

import utils.Constants._
import utils.Logger

import scalafx.Includes._
import scalafx.beans.property.DoubleProperty
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.image.{Image, ImageView}
import scalafx.util.converter.FormatStringConverter

/**
  * Created by faiaz on 15.12.16.
  */
trait Components extends Logger {

  @volatile var coefficient = DoubleProperty(1)
  val currencyFormat: NumberFormat = NumberFormat.getNumberInstance()
  val converter = new FormatStringConverter[Number](currencyFormat)

  lazy val choiceBox = new ChoiceBox[String] {
    maxWidth = 80
    maxHeight = 50
    items = ObservableBuffer(choice_1, choice_2, choice_3)
    selectionModel().selectFirst()
    selectionModel().selectedItem.onChange(
      (_, _, newValue) => newValue match {
        case `choice_1` =>
          log.info(s"$choice_1 is called, coefficient = ${Constant1()}")
          coefficient = Constant1
        case `choice_2` =>
          log.info(s"$choice_2 is called, coefficient = ${Constant2()}")
          coefficient = Constant2
        case `choice_3` =>
          log.info(s"$choice_3 is called, coefficient = ${Constant3()}")
          coefficient = Constant3
      }
    )
  }

  lazy val slider = new Slider(0, 100, 0) {
    minWidth = 400.0
  }

  lazy val textFiled = new TextField {
    textFormatter = new TextFormatter (converter) {
      value <== slider.value
    }
  }

  lazy val resultTextFiled = new TextField

  lazy val button = new Button("Click"){
    onAction = handle {
      log.info(s"${this.text} is pushed")
      resultTextFiled.setText(slider.value() * coefficient()) }
  }

  lazy val imageView = new ImageView {
    // image = new Image(getClass.getResource("epsya.jpg").toExternalForm)
       image = new Image("file:image.png")
    // image = new Image("http://www.scala-lang.org/resources/img/scala-logo.png")
  }
}
