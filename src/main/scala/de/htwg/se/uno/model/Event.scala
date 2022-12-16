package de.htwg.se.uno.model

trait Event()

case class Event_createGame() extends Event
case class Event_takeCard() extends Event
case class Event_playCard(chosenCard:Int) extends Event
case class Event_nextPlayer() extends Event
case class Event_IllegalMove() extends Event