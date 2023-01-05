package de.htwg.se.uno.controller
import de.htwg.se.uno.util._
import de.htwg.se.uno.model._


trait ControllerInterface extends Observable:
  var statement: String
  val undoManager: UndoManager
  var State : GameStateInterface
  var startFlag: Int
  def createPlayers(Namen:List[String]):Unit
  def printPlayers():Unit
  def createGame(): Unit
  def doStep(event: Event): GameStateInterface 
  def undo(): Unit
  def redo(): Unit
  def printFirstcard(): String
  def load: Unit
  def save: Unit


