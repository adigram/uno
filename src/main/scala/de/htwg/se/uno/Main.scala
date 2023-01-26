package de.htwg.se.uno
import model._  
import view._
import controller._
import com.google.inject.Guice
import scala.io.StdIn.readLine

object uno{

  val injector = Guice.createInjector(new ControllerModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = TUI(controller)
  val gui = GUI(controller)
  
  @main def main():Unit ={
    print("Hello! Please enter your Name in the Format:\nNamen1 Namen2")
    while (true) {
      var input = readLine()
       tui.input(input)
    }
  }
}