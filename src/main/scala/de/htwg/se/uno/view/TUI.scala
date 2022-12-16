package  de.htwg.se.uno.view
import scala.io.StdIn.readLine
import de.htwg.se.uno.controller._
import de.htwg.se.uno.util._
import de.htwg.se.uno.model.Player

class TUI extends Observer{

    val instruction = "Possible Instructions:\n\tt = Take a new Card from Stack\n" +
                      "\tp = Play Card\n" +
                      "\tu or uno = Call UNO\n" +
                      "\tuu or uno uno = Call UNO UNO\n"+
                      "\tq or quit = Leave the game\n"
    val select = "Please Select the Crad you want to drop.\nThe first card has the index 0." 

    val regexUno    = """^[U|u]no$""".r
    val regexUnoUno = """^[U|u]no ?[U|u]no$""".r
    val regexHelp   = """^[H|h]elp$""".r
    val regexQuit   = """^[Q|q]uit$""".r
    val regexTake   = """^[T|t]ake$""".r
    val regexPlay   = """^[P|p]lay$""".r

    def input() = {
        val input =  readLine()

        input match {
            case "t" | regexTake()   => Controller.request(TakeCard())
            case "p" | regexPlay()   => Controller.request(PlayCard(scanInt(())))
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
        println("Please enter Name of Player:")
        scan(())
    }

    override def update(change: Change): Unit = {
        change match {
            case change : NewCard  => println("Type newCard" + change.chosenCard)
            case change : SetupPlayers => setupPlayers
            case change : PlayerHands => println(change.players.map(player => player.toString).mkString)
            case change : TopCard  => println("Top Card:"+change.card.toString())
            case _                 => println("Unknow Change!")
        }
    }

    def setupPlayers : Unit = {
        println("How many Players?")
        val amount = scanInt(())
        Controller.request(ResponseSetupPlayers(amount,(0 until amount).map(k => getName(())).toList))
    }

}
