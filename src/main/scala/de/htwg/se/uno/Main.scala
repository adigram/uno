package de.htwg.se.uno
import model._  
import view._
import controller._

object uno{
  //val controller = new Controller()
  
  //val tui = TUI(controller)
  @main def main():Unit ={
    val test = new Card with wild with draw(2)
    println(test.action)
    // tui.start
    // while (true) { //endless while for test TODO
    //   tui.input()
    // }
  }
}