package  de.htwg.se.uno.view
import scala.io.StdIn.readLine
import de.htwg.se.uno.controller._
import de.htwg.se.uno.util._
import de.htwg.se.uno.model._
import de.htwg.se.uno.uno
import scala.util.Try
import scala.util.Success
import scala.util.Failure
import scala.util.matching.Regex

class TUI(ctrl:ControllerInterface) extends Observer{

    ctrl.add(this)
    ctrl.createGame()
      
    def input() = {
        if (ctrl.startFlag == 1) (println(instruction)) else println(startInstruction)  
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
            case regexNamen() =>  ctrl.createPlayers(input.split(" ").toList);ctrl.printPlayers();ctrl.printFirstcard()
            case _ => println("Wrong Input pls try again")
         }
         
    }
    override def update: Unit = println(ctrl.statement)

    def toInt(s: String): Option[Int] = {
         Try(s.toInt) match{
            case Success(value) => Some(s.toInt)
            case Failure(exception) => None
         }
    }

    def drop = ctrl.doStep(takeCardFromDeckEvent()).output.apply(0).equals('D')
    def choose = ctrl.doStep(dropLastCardEvent(None, true)).output.apply(0).equals('W')
    def choosecolour = ctrl.doStep(dropCardEvent(toInt(readLine()), false)).output.apply(0).equals('W')
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
val startInstruction = "Hello! Please enter your Name in the Format:\nNamen1 Namen2"
val regexNamen : Regex = """[0-9a-zA-Z- ]+[\s][0-9a-zA-Z- ]+$""".r