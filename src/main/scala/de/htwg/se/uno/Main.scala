package de.htwg.se.uno
import model._  
import view._
import controller._

object uno{
  val controller = new Controller()
  val tui = TUI(controller)
  @main def main():Unit ={
    tui.start
    while (true) {
       tui.input()
   }
  }
}