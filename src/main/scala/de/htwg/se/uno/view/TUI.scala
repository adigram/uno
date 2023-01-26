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
import com.google.inject.Guice


class TUI(ctrl:ControllerInterface) extends Observer{
    var flagInput = 1
    ctrl.add(this)
    ctrl.createGame()    
    def input(input:String) = {
        flagInput = 1
        if (ctrl.startFlag == 1){
            if(ctrl.State.output.apply(0).equals('W')){
                println("DETECTED W chose color. Input:"+input) // DEBUG
                ctrl.doStep(chooseColourEvent(toInt(input)))
            }else if(ctrl.State.output.apply(0).equals('D')){
                println("DETECTED D play or keep card") // DEBUG
                input match {
                    case "y" => ctrl.doStep(dropLastCardEvent(None))
                    case _   => ctrl.doStep(nextPlayerEvent())
                }
            }else{
            println("DETECTED normal input") // DEBUG
            input match {
                case "t"                 => ctrl.doStep(takeCardFromDeckEvent())
                case "r"                 => println(select);ctrl.doStep(dropCardEvent(toInt(readLine())))
                case "u" | regexUno()    => println(select);ctrl.doStep(UnoEvent(toInt(readLine())))
                case "uu"| regexUnoUno() => if(ctrl.doStep(UnoUnoEvent()).output.apply(0).equals('T')) System.exit(0)
                case "q" | regexQuit()   => System.exit(0)
                case "undo" => ctrl.undo()
                case "redo" => ctrl.redo()
                case "save" => ctrl.save
                case "load" => ctrl.load
                case _ => println("Wrong Input pls try again"); flagInput = 0
            }
            println(instruction)
            } 
        }
        else{
         println("DETECTED name input") // DEBUG
         input match {
                case regexNamen() =>  ctrl.createPlayers(input.split(" ").toList);ctrl.printPlayers();ctrl.printFirstcard()
                case _ => println("Wrong Input pls try again");println(startInstruction);flagInput = 0
            }
        }
         
    }
    override def update: Unit = {println(ctrl.statement)}

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
val startInstruction = "Hello! Please enter your Name in the Format:\nNamen1 Namen2\n"
val regexNamen : Regex = """[0-9a-zA-Z- ]+[\s][0-9a-zA-Z- ]+$""".r