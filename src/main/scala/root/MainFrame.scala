package main.scala.root

import main.scala.components.Components

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.GridPane
import scalafx.stage.Screen


/**
  * Created by faiaz on 15.12.16.
  */
object MainFrame extends JFXApp with Components {

  val screen = Screen.primary.bounds

  stage = new JFXApp.PrimaryStage {
    title = "Coursework"
    scene = new Scene(800, 800) {
      maxWidth = screen.maxX
      maxHeight = screen.maxY
      resizable = false

      root = new GridPane {
        hgap = 10
        vgap = 10
        add(slider, 10, 10)
        add(textFiled, 20, 20)
        add(button, 15, 15)
      }
    }
  }

}
