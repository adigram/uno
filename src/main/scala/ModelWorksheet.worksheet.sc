
case class Card(Colour: Int, Symbol: Int)
val Karte = Card(1,3)
val colour: List[String] = List[String]("red","green","blue","yellow","black")

val symbol: List[String] = List[String]( "0","1","2","3","4","5","6","7","8","9","🚫","🔃","+2","🌈","+4")

def createDeck(): Array[Card] =
    val cards = new Array[Card](109)
    for (i <- 1 to 4)
      cards(i) = Card((i % 4), (1))
    
    for(t <- 5 to 100)
        cards(t) = Card((((t-1)%4),(symbole)))
        
    for(y <- 101 to 108)
        cards(y) = Card(4,((y % 2)+13))
    cards

val deck = createDeck()
println(deck(1).Symbol.toString + " " + deck(1).Colour.toString)
for (u <- 1 to 108)
    println(colour.apply(deck(u).Colour)+" "+symbol.apply(deck(u).Symbol))
