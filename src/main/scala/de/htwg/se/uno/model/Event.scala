package de.htwg.se.uno.model

trait Event()
case class takeCardFromDeckEvent() extends Event
case class dropCardEvent(chosenCard:Int) extends Event
case class nextPlayerEvent() extends Event