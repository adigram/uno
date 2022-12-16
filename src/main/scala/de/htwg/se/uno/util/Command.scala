package de.htwg.se.uno.util

trait Command()

case class TakeCard() extends Command
case class PlayCard(chosenCard:Int) extends Command
case class Uno() extends Command
case class UnoUno() extends Command
case class ResponseSetupPlayers(amount: Int,names: List[String]) extends Command