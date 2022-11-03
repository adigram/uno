package  de.htwg.se.uno.view
import scala.io.StdIn.readLine
import de.htwg.se.uno.model._ 
import scala.collection.mutable._
import scala.util.Random as r 
class TUI() {

    def start = {
        CardDeck.createDeck()
        CardDeck.shuffle(r)
        println("Hello! Please enter the amount of Player.")
        var players = createPlayers(readLine().toInt)
        players.foreach{k => print(k)}
        println("Stack: " + CardDeck.takeCard(1).mkString(""))
    }

    var scan:(Unit => String) = Unit =>  scala.io.StdIn.readLine()

    def getName(): String ={
        println("Please enter your Name:")
        val name = scan(())
        name 
    }
  
    def createPlayers(players: Int):List[(Player)]=(0 until players).map(k =>Player(CardDeck.takeCard(7),getName())).toList                                                                     
}
