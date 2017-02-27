package stages

import components.Components
import utils.{Piezoceramic, Quartz, Titan}

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

  val quartzObs: ObservableBuffer[Quartz] = Components.quartzTable
  val titanObs: ObservableBuffer[Titan] = Components.titanTable
  val tourmalineObs: ObservableBuffer[Piezoceramic] = Components.piezoceramicTable

  scene = new Scene {
    content = new HBox {
      children = Seq(
        //QUARTZ
        new TableView[Quartz](quartzObs) {
          columns ++= List(
            new TableColumn[Quartz, String] {
              text = "Сила, F"
              cellValueFactory = input => input.value.inputVal
              prefWidth = 100
            },
            new TableColumn[Quartz, String] {
              text = "Напруга, V"
              cellValueFactory = output => output.value.outputVal
              prefWidth = 100
            }
          )
        },
        //TITAN
        new TableView[Titan](titanObs) {
          columns ++= List(
            new TableColumn[Titan, String] {
              text = "Сила, F"
              cellValueFactory = input => input.value.inputVal
              prefWidth = 100
            },
            new TableColumn[Titan, String] {
              text = "Напруга, V"
              cellValueFactory = output => output.value.outputVal
              prefWidth = 100
            }
          )
        },
        //TOURMALINE
        new TableView[Piezoceramic](tourmalineObs) {
          columns ++= List(
            new TableColumn[Piezoceramic, String] {
              text = "Сила, F"
              cellValueFactory = input => input.value.inputVal
              prefWidth = 100
            },
            new TableColumn[Piezoceramic, String] {
              text = "Напруга, V"
              cellValueFactory = output => output.value.outputVal
              prefWidth = 100
            }
          )
        }
      )
    }
  }
}
