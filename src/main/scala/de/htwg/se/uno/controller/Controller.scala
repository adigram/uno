package de.htwg.se.uno.controller

import de.htwg.se.uno.util._
import de.htwg.se.uno.model._

import scala.util.Random as random 
import de.htwg.se.uno.uno

class Controller() extends Observable{
    var statement = ""
    var State = state(0,List[Player](),true,List[Card](),List[Card](),"a")
    val undoManager = new UndoManager
    
    def createGame() =
        CardDeck.shuffle(random)
        State = State.copy(stack = (CardDeck.takeCard(1)))
        
    def createPlayers(Namen:List[String]) = 
        State = State.copy(deck = CardDeck.deck, players = (0 until 2).map(k =>Player(CardDeck.takeCard(7),Namen.apply(k))).toList )
        statement = "Players created!\n"
        notifyObservers

    def printPlayers() =  
        statement = State.players.map(k => k.toString).mkString
        notifyObservers

   
    def doStep(event: Event): state = {
        undoManager.doStep(new SetCommand(event,this))
        notifyObservers
        return this.State
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
