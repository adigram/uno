package de.htwg.se.uno.controller

import de.htwg.se.uno.util._
import de.htwg.se.uno.model._

import scala.util.Random as random 
import de.htwg.se.uno.uno

class Controller() extends Observable{
    var statement = ""
    var State = state(0,List[Player](),true,List[Card](),List[Card](),"")
    val undoManager = new UndoManager
    
    def createGame() =
        CardDeck.shuffle(random)
        State = State.copy(stack = (CardDeck.takeCard(1)))
        
    def createPlayers(players: Int,getName:(Unit => String)) = 
        State = State.copy(deck = CardDeck.deck, players = (0 until players).map(k =>Player(CardDeck.takeCard(7),getName(()))).toList )

    def printPlayers() =  
        statement = State.players.map(k => k.toString).mkString
        notifyObservers

   
    def doStep(event: Event): String = {
        undoManager.doStep(new SetCommand(event,this))
        notifyObservers
        return statement
    }

    def undo(): Unit =
        undoManager.undoStep

    def redo(): Unit =
        undoManager.redoStep
        notifyObservers

    def printFirstcard(): String = 
        statement =  "Stack: " + State.stack(0).toString
        notifyObservers
        return statement
}   
