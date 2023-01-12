package de.htwg.se.uno.controller

import de.htwg.se.uno.util._
import de.htwg.se.uno.model._
import com.google.inject.{Guice, Inject}
import de.htwg.se.uno.UnoModule
import scala.util.Random as random 
import de.htwg.se.uno.uno

case class  Controller @Inject()(var State: GameStateInterface) extends ControllerInterface with Observable:
    var statement = ""
    val undoManager = new UndoManager
    var startFlag = 0
    def createGame() =
        this.State =  State.createGame()
        
    def createPlayers(Namen:List[String]): String = 
        State = State.createPlayers(Namen)
        startFlag = 1
        statement = "Players created!\n"
        notifyObservers
        statement

    def printPlayers() =  
        statement = State.players.map(k => k.toString).mkString
        notifyObservers

   
    def doStep(event: Event): GameStateInterface = {
        undoManager.doStep(new SetCommand(event,this))
        notifyObservers
        return this.State
    }

    def save: Unit =
     def fileIO =
        Guice.createInjector(new UnoModule).getInstance(classOf[FileIOInterface])
     fileIO.save(State)
     statement = "Save Done"
     notifyObservers



    def load: Unit =
     def fileIO =
        Guice.createInjector(new UnoModule).getInstance(classOf[FileIOInterface])
     State = fileIO.load
     statement = State.output
     notifyObservers

    def undo(): Unit =
        undoManager.undoStep

    def redo(): Unit =
        undoManager.redoStep
        notifyObservers

    def printFirstcard(): String = 
        statement =  "Stack: " + State.stack(0).toString
        notifyObservers
        return statement
  
