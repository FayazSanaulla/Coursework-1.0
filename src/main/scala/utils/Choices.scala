package utils

/**
  * Created by faiaz on 27.12.16.
  */
object Choices extends Enumeration {
  type Choice = Value
  val TITAN = Value("Титанат Барію")
  val QUARTZ = Value("Кварц")
  val TOURMALINE = Value("Турмалін")
}

object ChoicesValue {
  val quartzValue = 0.9
  val titanValue = 1.25
  val tourmalineValue = 1.5
}


