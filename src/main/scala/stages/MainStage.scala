package stages

import components.Components._
import components.Images.functionalSchema

import scalafx.application.JFXApp
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.layout.{BorderPane, GridPane}

/**
  * Created by faiaz on 15.12.16.
  */
object MainStage extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "П'єзоелектричний перетворювач тиску"
    scene = new Scene(830, 700) {
      resizable = false

      root = new BorderPane {
        top = menuBer
        center = new GridPane() {
          alignment = Pos.Center
          hgap = 5
          vgap = 5

          add(label("F, (H)"), 10, 15)
          add(slider, 10, 16)

          add(label("Вхідна сила, H"), 5, 14)
          add(sliderTextFiled, 5, 15)

          add(label("П'єзоелектрики"), 5, 1)
          add(vbox, 5, 2)

          add(label("Схематичне зображення"), 10, 1)
          add(functionalSchema, 10, 2)

          add(label("Вихідна напруга, B"), 12, 14)
          add(resultTextFiled, 12, 15)
        }
      }
    }
  }

}
