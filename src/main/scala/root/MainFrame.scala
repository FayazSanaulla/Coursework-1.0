package root

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
    title = "П'єзоелектричний перетворювач тиску"
    scene = new Scene(800, 700) {
      resizable = false

      root = new GridPane {
        alignment = Pos.Center
        hgap = 5
        vgap = 5

        add(label("Шкала"), 10, 15)
        add(slider, 10, 16)

        add(label("Вхідне значення"), 5, 14)
        add(sliderTextFiled, 5, 15)

        add(label("Можливі варіанти"), 5, 1)
        add(choiceBox, 5, 2)

        add(label("Схема"), 10, 1)
        add(imageView, 10, 2)

        add(label("Результат"), 12, 14)
        add(resultTextFiled, 12, 15)
      }
    }
  }

}
