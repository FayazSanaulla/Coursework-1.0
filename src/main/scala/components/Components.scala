package components

import math.Math._
import stages.{ChartStage, SchemaStage, TableStage}
import utils.Implicits._
import utils.Piezomodule._
import utils.RichDouble.DoubleExpansion
import utils.{Choices, Piezoceramic, Quartz, Titan}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.image.ImageView
import scalafx.scene.input.KeyCombination
import scalafx.scene.layout.{Pane, VBox}
/**
  * Created by faiaz on 18.12.16.
  */

object Components {

  type Pair = (Double, Double)

  val quartzTable: ObservableBuffer[Quartz] = ObservableBuffer[Quartz]()
  val piezoceramicTable: ObservableBuffer[Piezoceramic] = ObservableBuffer[Piezoceramic]()
  val titanTable: ObservableBuffer[Titan] = ObservableBuffer[Titan]()

  val quartzChart: ArrayBuffer[Pair] = mutable.ArrayBuffer[Pair]()
  val piezoceramicChart: ArrayBuffer[Pair] = mutable.ArrayBuffer[Pair]()
  val titanChart: ArrayBuffer[Pair] = mutable.ArrayBuffer[Pair]()

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

  val vbox = new VBox {
    spacing = 5
    children = Seq(choiceBox)
  }

  var imageView = new ImageView("image.png")

  val menuBer = new MenuBar {
    autosize()
    menus = List(
      new Menu("Побудова") {
        items = List(
          new MenuItem("Графік") {
            onAction = handle(new ChartStage().showAndWait())
            accelerator = KeyCombination.keyCombination("Alt + G")
          },
          new MenuItem("Таблиця") {
            onAction = handle(new TableStage().showAndWait())
            accelerator = KeyCombination.keyCombination("Alt + T")
          },
          new MenuItem("Оновити") {
            onAction = handle(reset)
            accelerator = KeyCombination.keyCombination("Alt + O")
          }
        )
      },
      new Menu("Схеми") {
        items = List(
          new MenuItem("Схема принципова") {
            onAction = handle(new SchemaStage().showAndWait())
            accelerator = KeyCombination.keyCombination("Alt + S")
          }
        )
      }
    )
  }

  val tabPane = new TabPane {
    tabs = Seq(
      new Tab {
        text = "Схема 1"
        closable = false
      },
      new Tab {
        text = "Схема 2"
        closable = false
      }
    )
  }

  def label(text: String) = new Label(text)

  private def piezimodule(choice: Choices.Value): Double = choice match {
    case Choices.QUARTZ => quartzModule
    case Choices.PIEZOCARAMIC => piezoceramicModule
    case Choices.TITAN => titanModule
  }

  private def reset = {
    quartzTable.clear()
    piezoceramicTable.clear()
    titanTable.clear()
    quartzChart.clear()
    piezoceramicChart.clear()
    titanChart.clear()
  }
}
