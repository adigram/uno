package de.htwg.se.uno

import com.google.inject.AbstractModule
import de.htwg.se.uno.controller._
import de.htwg.se.uno.model._

class UnoModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ControllerInterface]).toInstance(new Controller())
  }
}
