package de.htwg.se.uno
import model._  
import view._
import controller._

object uno{
  val tui = TUI()
  @main def main():Unit ={
    Controller.add(tui)
    //Controller.add(gui)
    Controller.createGame()
    while (true) {
       tui.input()
    }
  }
}

