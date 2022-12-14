package de.htwg.se.uno.controller

import de.htwg.se.uno.util._
import de.htwg.se.uno.model._

import scala.util.Random as random 
import de.htwg.se.uno.uno

case class Controller() extends ControllerInterface with Observable:
    var statement = ""
    var State = state(0,List[Player](),true,List[Card](),List[Card](),"a", Trigger.print)
    val undoManager = new UndoManager
    var startFlag = 0
    def createGame() =
        CardDeck.shuffle(random)
        State = State.copy(stack = (CardDeck.takeCard(1)))
        
    def createPlayers(Namen:List[String]) = 
        State = State.copy(deck = CardDeck.deck, players = (0 until 2).map(k =>Player(CardDeck.takeCard(7),Namen.apply(k))).toList )
        startFlag = 1
        statement = "Players created!\n"
        notifyObservers(Trigger.print)

    def printPlayers() =  
        statement = State.players.map(k => k.toString).mkString
        notifyObservers(Trigger.print)

   
    def doStep(event: Event): GameStateInterface = {
        undoManager.doStep(new SetCommand(event,this))
        notifyObservers(State.trigger)
        return this.State
    }

    def undo(): Unit =
        undoManager.undoStep

    def redo(): Unit =
        undoManager.redoStep
        notifyObservers(State.trigger)

    def printFirstcard(): String = 
        statement =  "Stack: " + State.stack(0).toString
        notifyObservers(Trigger.print)
        return statement
  
