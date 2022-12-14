package de.htwg.se.uno.controller
import de.htwg.se.uno.util._
import de.htwg.se.uno.model._


trait ControllerInterface extends Observable:
  var statement: String
  var State: state
  val undoManager: UndoManager
  var startFlag: Int
  def createGame():Unit
  def takeCard(n: Int) : List[Card]
  def createPlayers(Namen:List[String]):Unit
  def printPlayers():Unit
  def doStep(event: Event): GameStateInterface 
  def undo(): Unit
  def redo(): Unit
  def printFirstcard(): String



