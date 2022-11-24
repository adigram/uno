package de.htwg.se.uno.controller

import de.htwg.se.uno.util._
import de.htwg.se.uno.model._
import scala.util.Random as random 
import de.htwg.se.uno.uno

class Controller() extends Observable{
    var statement = ""
    //var players = List[(Player)]()
    var State = state(0,List[Player](),true,List[Card](),List[Card](),"")
    def createGame() =
        CardDeck.shuffle(random)
        State = State.copy(stack = (CardDeck.takeCard(1)))
        
    def createPlayers(players: Int,getName:(Unit => String)) = 
        State = State.copy(deck = CardDeck.deck, players = (0 until players).map(k =>Player(CardDeck.takeCard(7),getName(()))).toList )

    def printPlayers() =  
        statement = State.players.map(k => k.toString).mkString
        notifyObservers

    def takeCard(): Unit = print("takecard")
        //takeCardCreato.apply(CardDeck.deck)
    def handle(event: Event) = {
        State = State.handle(event)
        statement = State.output
        notifyObservers
    }

    def printFirstcard() = 
        statement =  "Stack: " + State.stack(0).toString
        notifyObservers
}   
