package de.htwg.se.uno.controller

import de.htwg.se.uno.util._
import de.htwg.se.uno.model._
import scala.util.Random as random 
import de.htwg.se.uno.uno

class Controller() extends Observable{
    var statement = ""
    var players = List[(Player)]()
    var State = state(0,List[Player](),true,List[Card](),List[Card](),"")
    def createGame() =
        CardDeck.shuffle(random)
        CardDeck.stack = (CardDeck.takeCard(1))
        

    def createPlayers(players: Int,getName:(Unit => String)) = 
        this.players = (0 until players).map(k =>Player(CardDeck.takeCard(7),getName(()))).toList

    def printPlayers() =
        State = state(0,players, true, CardDeck.deck, CardDeck.stack,"hii") 
        statement = players.map(k => k.toString).mkString
        notifyObservers

    def takeCard(): Unit = print("takecard")
        //takeCardCreato.apply(CardDeck.deck)
    def handle(event: Event) = {
        State = State.handle(event)
        statement = State.output
        notifyObservers
        
    }

    def printFirstcard() = 
        statement =  "Stack: " + CardDeck.stack(0).toString
        notifyObservers
}   
