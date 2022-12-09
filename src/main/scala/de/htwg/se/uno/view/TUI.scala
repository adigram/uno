package  de.htwg.se.uno.view
import scala.io.StdIn.readLine
import de.htwg.se.uno.controller._
import de.htwg.se.uno.util._
import de.htwg.se.uno.model._
import de.htwg.se.uno.uno
import scala.util.Try
import scala.util.Success
import scala.util.Failure

class TUI(ctrl:Controller) extends Observer{

    ctrl.add(this)
    ctrl.createGame()

    def start = {
        
        println("Hello! Please enter your Name:")
        val name1 = readLine()
        println("Hello! Please enter your Name:")
        val name2 = readLine()
        ctrl.createPlayers(List(name1, name2))
        ctrl.printPlayers()
        ctrl.printFirstcard() 
    }

            
            
    def input() = {
        println(instruction)
        val input =  readLine()
        input match {
            case "t" => {
                         if(ctrl.doStep(takeCardFromDeckEvent()).output.apply(0).equals('D'))
                            readLine() match {
                                case "y" => if(ctrl.doStep(dropLastCardEvent(None, true)).output.apply(0).equals('W'))
                                            ctrl.doStep(chooseColourEvent(toInt(readLine()))) 
                                case "n" => ctrl.doStep(nextPlayerEvent())
                                case _   => ctrl.doStep(nextPlayerEvent())
                            }
                        }
            case "r" => {
                        println(select)
                        if(ctrl.doStep(dropCardEvent(toInt(readLine()), false)).output.apply(0).equals('W'))
                            ctrl.doStep(chooseColourEvent(toInt(readLine()))) 
                        }
            case "u" | regexUno()    => println(select);ctrl.doStep(dropCardEvent(toInt(readLine()), true))
            case "uu"| regexUnoUno() => if(ctrl.doStep(UnoUnoEvent()).output.apply(0).equals('T')) System.exit(0)
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

    def toInt(s: String): Option[Int] = {
         Try(s.toInt) match{
            case Success(value) => Some(s.toInt)
            case Failure(exception) => None
         }
    }
  
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
