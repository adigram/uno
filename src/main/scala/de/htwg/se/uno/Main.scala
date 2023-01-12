package de.htwg.se.uno
import model._  
import view._
import controller._
import com.google.inject.Guice


object uno{
  
  val injector = Guice.createInjector(new UnoModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = TUI(controller)
  
  @main def main():Unit ={
    val gui = GUI1(controller)
    while (true) {
       tui.input()
    }
  }
}