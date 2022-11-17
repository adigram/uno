package  de.htwg.se.uno.model 
import de.htwg.se.uno.uno


 case class Player(hand: List[Card], name: String){
    override def toString = 
    "Hand Player: "  + name +"\n" + hand.map(k=> k.toString).mkString("  |  ")  + "\n" 
    
    
 }
    