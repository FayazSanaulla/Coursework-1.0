package stages

import components.Components
import utils.{Quartz, Titan, Tourmaline}

import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableColumn, TableView}
import scalafx.scene.layout.HBox
import scalafx.stage.Stage

/**
  * Created by faiaz on 05.01.17.
  */
class TableStage extends Stage {

  val quartzObs: ObservableBuffer[Quartz] = Components.quartzArr
  val titanObs: ObservableBuffer[Titan] = Components.titanArr
  val tourmalineObs: ObservableBuffer[Tourmaline] = Components.tourmalineArr

  scene = new Scene {
    content = new HBox {
      children = Seq(
        //QUARTZ
        new TableView[Quartz](quartzObs) {
          columns ++= List(
            new TableColumn[Quartz, String] {
              text = "Input"
              cellValueFactory = input => input.value.inputVal
              prefWidth = 100
            },
            new TableColumn[Quartz, String] {
              text = "Output"
              cellValueFactory = output => output.value.outputVal
              prefWidth = 100
            }
          )
        },
        //TITAN
        new TableView[Titan](titanObs) {
          columns ++= List(
            new TableColumn[Titan, String] {
              text = "Input"
              cellValueFactory = input => input.value.inputVal
              prefWidth = 100
            },
            new TableColumn[Titan, String] {
              text = "Output"
              cellValueFactory = output => output.value.outputVal
              prefWidth = 100
            }
          )
        },
        //TOURMALINE
        new TableView[Tourmaline](tourmalineObs) {
          columns ++= List(
            new TableColumn[Tourmaline, String] {
              text = "Input"
              cellValueFactory = input => input.value.inputVal
              prefWidth = 100
            },
            new TableColumn[Tourmaline, String] {
              text = "Output"
              cellValueFactory = output => output.value.outputVal
              prefWidth = 100
            }
          )
        }
      )

  }
}
}
