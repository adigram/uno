package  de.htwg.se.uno.view
import scala.io.StdIn.readLine
import de.htwg.se.uno.controller._
import de.htwg.se.uno.util._

class TUI(ctrl:Controller) extends Observer{

    ctrl.add(this)
    ctrl.createGame()
    
    def start = {
        println("Hello! Please enter the amount of Player.")
        ctrl.createPlayers(readLine().toInt,getName)
        ctrl.printPlayers()
        ctrl.printFirstcard()
    }

    var scan:(Unit => String) = Unit =>  scala.io.StdIn.readLine()

    val getName:(Unit => String) = Unit => {
        println("Please enter your Name:")
        val name = scan(())
        name 
    }

    override def update: Unit = println(ctrl.statement)
  
}
