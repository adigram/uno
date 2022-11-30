package de.htwg.se.uno.controller

import de.htwg.se.uno.uno.controller._
import de.htwg.se.uno.model._
import de.htwg.se.uno.util._

class SetCommand(input: Event,ctrl:Controller) extends Command {

  val state: state = ctrl.State

  override def doStep(): Unit =
    val result = ctrl.State.handle(input)
    ctrl.State = result
    ctrl.statement = state.output

  override def undoStep(): Unit =
    ctrl.State = state
    ctrl.statement = state.output
    ctrl.notifyObservers

  override def redoStep(): Unit =
    val result = ctrl.State.handle(input)
    ctrl.State = result
    ctrl.statement = state.output
}