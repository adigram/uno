package  de.htwg.se.uno.view
import scala.io.StdIn.readLine
import de.htwg.se.uno.controller._
import de.htwg.se.uno.util._
import de.htwg.se.uno.model._
import de.htwg.se.uno.uno

class TUI(ctrl:Controller ) extends Observer{

    ctrl.add(this)
    ctrl.createGame()
    
    def start = {
        println("Hello! Please enter the amount of Player.")
        ctrl.createPlayers(readLine().toInt,getName)
        ctrl.printPlayers()
        ctrl.printFirstcard()
    }

    val regexUno    = """^[U|u]no$""".r
    val regexUnoUno = """^[U|u]no ?[U|u]no$""".r
    val regexQuit   = """^[Q|q]uit$""".r

    def input() = {
        val input =  readLine()

        input match {
            case "u" | regexUno()    => println("Uno!")
            case "uu"| regexUnoUno() => println("Uno Uno!")
            case "q" | regexQuit()   => System.exit(0)
            case _ => println("Wrong Input pls try again")
         }
    }

    var scan:(Unit => String) = Unit =>  scala.io.StdIn.readLine()

    val getName:(Unit => String) = Unit => {
        println("Please enter your Name:")
        val name = scan(())
        name 
    }

    override def update: Unit = println(ctrl.statement)
  
}
