package de.htwg.se.uno.util

import de.htwg.se.uno.model.Card
import de.htwg.se.uno.model.Player

trait Change()

case class NewCard(chosenCard:Int) extends Change
case class SetupPlayers() extends Change
case class TopCard(card:Card) extends Change
case class PlayerHands(players:List[Player]) extends Change