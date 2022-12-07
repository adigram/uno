package de.htwg.se.uno.model

trait Command()

case class TakeCard() extends Command
case class PlayCard(chosenCard:Int) extends Command
case class NextPlayer() extends Command
case class IllegalMove() extends Command
case class Uno() extends Command
case class UnoUno() extends Command