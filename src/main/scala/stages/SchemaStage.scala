package stages

import scalafx.scene.Scene
import scalafx.scene.image.ImageView
import scalafx.stage.Stage

/**
  * Created by faiaz on 19.03.17.
  */
class SchemaStage extends Stage {
  scene = new Scene {
    content = new ImageView("piezo.png")
  }
}
