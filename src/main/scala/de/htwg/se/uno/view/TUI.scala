package  de.htwg.se.uno.view
import scala.io.StdIn.readLine
import de.htwg.se.uno.controller._
import de.htwg.se.uno.util._
import de.htwg.se.uno.model._
import de.htwg.se.uno.uno

class TUI(ctrl:Controller) extends Observer{

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

    val instruction = "Possible Instructions:\n\tt = Take a new Card from Stack\n" +
                      "\tr = Put a Card from Hand into GameBoard\n" +
                      "\tu or uno = Call UNO\n" +
                      "\tuu or uno uno = Call UNO UNO\n"+
                      "\tq or quit = Leave the game\n"+
                      "\tundo = State backwards\n"+
                      "\tredo = repeat State\n"
                      
    val select = "Please Select the Crad you want to drop.\nThe first card has the index 0."         
            
    def input() = {
        println(instruction)
        val input =  readLine()


        input match {
            case "t" => ctrl.doStep(takeCardFromDeckEvent())
            case "r" => {
                         println(select)
                         val selectedCard = toInt(readLine())
                         if(selectedCard>=0)
                            ctrl.doStep(dropCardEvent(selectedCard))
                        }
            case "u" | regexUno()    => println("Uno!")
            case "uu"| regexUnoUno() => println("Uno Uno!")
            case "q" | regexQuit()   => System.exit(0)
            case "undo" => ctrl.undo()
            case "redo" => ctrl.redo()
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

    def toInt(s: String): Int = {
         try {
            s.toInt
         } 
         catch {
            case e: Exception => -1
         }
    }
  
}
