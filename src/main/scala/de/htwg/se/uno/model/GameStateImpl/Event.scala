package de.htwg.se.uno.model

trait Event()
case class takeCardFromDeckEvent() extends Event
case class dropCardEvent(chosenCard:Option[Int], unoFlag:Boolean) extends Event
case class nextPlayerEvent() extends Event
case class dropLastCardEvent(dropChosenCard:Option[Int], unoFlag:Boolean) extends Event
case class chooseColourEvent(chooseColour:Option[Int]) extends Event
case class UnoUnoEvent() extends Event