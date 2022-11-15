package de.htwg.se.uno.controller

import de.htwg.se.uno.util._
import de.htwg.se.uno.model._
import scala.util.Random as random 

class Controller extends Observable{
    var statement = ""
    var players = List[(Player)]()

    def createGame() =
        CardDeck.shuffle(random)

    def createPlayers(players: Int,getName:(Unit => String)) = 
        this.players = (0 until players).map(k =>Player(CardDeck.takeCard(7),getName(()))).toList

    def printPlayers() = 
        statement = players.map(k => k.toString).mkString
        notifyObservers
    def getCardStack() =
        CardsAction.getCards(k)
    def dropCards() = 
        CardsAction.dropCards(k,1)
    
    def printFirstcard() = 
        statement =  "Stack: " + CardDeck.takeCard(1).mkString
        notifyObservers
}   
