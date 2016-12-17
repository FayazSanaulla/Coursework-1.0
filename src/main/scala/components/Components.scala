package components

import java.text.NumberFormat

import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.image.{Image, ImageView}
import scalafx.util.converter.FormatStringConverter

/**
  * Created by faiaz on 15.12.16.
  */
trait Components {

  val currencyFormat = NumberFormat.getNumberInstance()
  val converter = new FormatStringConverter[Number](currencyFormat)

  lazy val slider = new Slider(0, 100, 0) {
    minWidth = 400.0
  }

  lazy val textFiled = new TextField {
    textFormatter = new TextFormatter (converter) {
      value <==> slider.value
    }
  }

  lazy val button = new Button("Click"){
    onAction = handle { new Alert(AlertType.Information, "Info Message").showAndWait() }
  }

  lazy val imageView = new ImageView {
    // image = new Image(getClass.getResource("epsya.jpg").toExternalForm)
       image = new Image("file:image.png")
    // image = new Image("http://www.scala-lang.org/resources/img/scala-logo.png")
  }

  lazy val choiseBox = new ChoiceBox[String] {
    maxWidth = 80
    maxHeight = 50
    items = ObservableBuffer("Test1", "Test2", "Test3")
    selectionModel().selectFirst()
    selectionModel().selectedItem.onChange(
      (_, _, newValue) => newValue match {
        case "Test1" => textFiled.setText("4")
        case "Test2" => textFiled.setText("5")
        case "Test3" => textFiled.setText("6")
      }
    )
  }
}
