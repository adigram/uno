package  de.htwg.se.uno.view
import scala.io.StdIn.readLine
import de.htwg.se.uno.controller._
import de.htwg.se.uno.util._
import de.htwg.se.uno.model._
import de.htwg.se.uno.uno

class TUI extends Observer{

    Controller.add(this)
    Controller.createGame()
    
    def start = {
        println("How many Players?")
        Controller.createPlayers(scanInt(()),getName)
        println(instruction)
        Controller.printPlayers()
        Controller.printFirstcard()  
    }

    val instruction = "Possible Instructions:\n\tt = Take a new Card from Stack\n" +
                      "\tr = Put a Card from Hand into GameBoard\n" +
                      "\tu or uno = Call UNO\n" +
                      "\tuu or uno uno = Call UNO UNO\n"+
                      "\tq or quit = Leave the game\n"
    val select = "Please Select the Crad you want to drop.\nThe first card has the index 0." 

    val regexUno    = """^[U|u]no$""".r
    val regexUnoUno = """^[U|u]no ?[U|u]no$""".r
    val regexHelp   = """^[H|h]elp$""".r
    val regexQuit   = """^[Q|q]uit$""".r

    def input() = {
        val input =  readLine()

        input match {
            case "t"                 => Controller.request(TakeCard())
            case "r"                 => Controller.request(PlayCard(scanInt(())))
            case "u" | regexUno()    => Controller.request(Uno())
            case "uu"| regexUnoUno() => Controller.request(UnoUno())
            case "h" | regexHelp()   => println(instruction)
            case "q" | regexQuit()   => System.exit(0)
            case _                   => println("Wrong Input pls try again")
         }
    }

    var scan:(Unit => String) = Unit =>  scala.io.StdIn.readLine()
    var scanInt:(Unit => Int) = Unit =>  scala.io.StdIn.readInt()

    val getName:(Unit => String) = Unit => {
        println("Please enter your Name:")
        val name = scan(())
        name 
    }

    override def update: Unit = println(Controller.statement)

}
