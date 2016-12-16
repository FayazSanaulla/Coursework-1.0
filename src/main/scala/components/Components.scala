package main.scala.components

import java.text.NumberFormat
import java.util.Locale

import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.util.converter.FormatStringConverter
import scalafx.Includes._

/**
  * Created by faiaz on 15.12.16.
  */
trait Components {

  val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)
  val converter = new FormatStringConverter[Number](currencyFormat)

  lazy val slider = new Slider(0, 100, 0)

  lazy val textFiled = new TextField {
    textFormatter = new TextFormatter (converter) {
      value <==> slider.value
    }
  }

  lazy val button = new Button("Click"){
    onAction = handle { new Alert(AlertType.Information, "Info Message").showAndWait() }
  }
}
