package stages

import components.Images.principeSchema

import scalafx.scene.Scene
import scalafx.stage.Stage

/**
  * Created by faiaz on 19.03.17.
  */
class SchemaStage extends Stage {
  scene = new Scene {
    content = principeSchema
  }
}
