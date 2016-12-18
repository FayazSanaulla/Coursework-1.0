package root

import components.Components
import components.Components._

import scalafx.application.JFXApp
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.layout.GridPane
import scalafx.stage.Screen

/**
  * Created by faiaz on 15.12.16.
  */
object MainFrame extends JFXApp {

  val screen = Screen.primary.bounds

  stage = new JFXApp.PrimaryStage {
    title = "Coursework"
    scene = new Scene(800, 800) {
      maxWidth = screen.maxX
      maxHeight = screen.maxY
      resizable = false

      root = new GridPane {
        alignment = Pos.Center
        hgap = 5
        vgap = 5
        add(slider, 10, 50)
        add(sliderTextFiled, 8, 50)
        add(choiceBox, 4, 4)
        //add(imageView, 1, 1)
        add(resultTextFiled, 12, 12)
      }
    }
  }

}
