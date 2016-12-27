package utils

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
  * Created by faiaz on 18.12.16.
  */
trait Logger {
  val log = Logger(LoggerFactory.getLogger(this.getClass))
}
