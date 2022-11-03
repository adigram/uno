package  de.htwg.se.uno.model 
 case class Player(hand: List[Card], name: String){
    override def toString = 
    "Hand Player: "  + name +"\n" + hand.map(k=> k.toString).mkString("  |  ")  + "\n"
 }
    