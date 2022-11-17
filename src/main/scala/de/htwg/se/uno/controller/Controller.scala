package de.htwg.se.uno.controller

import de.htwg.se.uno.util._
import de.htwg.se.uno.model._
import scala.util.Random as random 

class Controller(var game: Game) extends Observable{
    var statement = ""
    var players = List[(Player)]()
    

    def createGame() =
        CardDeck.shuffle(random)
        CardDeck.stack = (CardDeck.takeCard(1))

    def createPlayers(players: Int,getName:(Unit => String)) = 
        this.players = (0 until players).map(k =>Player(CardDeck.takeCard(7),getName(()))).toList

    def printPlayers() = 
        statement = players.map(k => k.toString).mkString
        notifyObservers

    def handle(event: Event): Unit = {
        state.handle(event)
    }
    def takeCard(): Unit = {
        takeCardCreato.apply(CardDeck.deck)
    }
    
    
    def printFirstcard() = 
        statement =  "Stack: " + CardDeck.stack(0).toString
        notifyObservers
}   
