package de.htwg.se.uno

import com.google.inject.AbstractModule
import de.htwg.se.uno.controller._
import de.htwg.se.uno.model._


class IOJSON extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[FileIOInterface]).toInstance(new fileIoJsonImpl.FileIoJson())
  }
}

class IOXML extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[FileIOInterface]).toInstance(new fileIoXmlImpl.FileIOXml())
  }
}

class ControllerModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ControllerInterface]).toInstance(new Controller(new state()))
  }
}



