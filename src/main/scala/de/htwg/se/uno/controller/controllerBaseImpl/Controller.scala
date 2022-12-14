package de.htwg.se.uno.controller

import de.htwg.se.uno.util._
import de.htwg.se.uno.model._

import scala.util.Random as random 
import de.htwg.se.uno.uno

case class Controller() extends ControllerInterface with Observable:
    var statement = ""
    var State = state(0,List[Player](),true,List[Card](),List[Card](),"a")
    val undoManager = new UndoManager
    var startFlag = 0
    def createGame() =
        State = State.copy(deck = random.shuffle(State.createDeck().deck) )
        val stack= takeCard(1)
        State = State.copy(stack = stack)

    def takeCard(n: Int) = {var split = State.deck.splitAt(n); State = State.copy(deck = split._2); split._1}
        
    def createPlayers(Namen:List[String]) = 
        State = State.copy( players = (0 until 2).map(k =>Player(takeCard(7),Namen.apply(k))).toList )
        startFlag = 1
        statement = "Players created!\n"
        notifyObservers

    def printPlayers() =  
        statement = State.players.map(k => k.toString).mkString
        notifyObservers

   
    def doStep(event: Event): GameStateInterface = {
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
  