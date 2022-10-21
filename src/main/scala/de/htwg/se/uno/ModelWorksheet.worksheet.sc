println("Hello, world!")
   
val colour = List("🟥","🟩","🟦","🟨","⬛")
  
val symbol = List( "0","1","2","3","4","5","6","7","8","9","🚫","🔃","+2","🌈","+4")

def hands():List[(Int,Int)]= List( (0,5),(0,10),(3,12),(2,3),(1,8) )
hands()
def playershand(player: Int): String = 
    "Hand Player "  + player +"\n" + hands().map(k=> colour.apply(k._1) +" "+symbol.apply(k._2)).mkString("  |  ")  + "\n"

playershand(1)  

case class Card(Colour: Int, Symbol: Int)

def createDeck(): Array[Card] =
    val cards = new Array[Card](109)
    for (i <- 1 to 4)
      cards(i) = Card((i % 4), (1))
    
    for(t <- 5 to 100)
        cards(t) = Card( (t%4),(t%15) )
        
    for(y <- 101 to 108)
        cards(y) = Card(4,((y % 2)+13))
    cards

createDeck()
