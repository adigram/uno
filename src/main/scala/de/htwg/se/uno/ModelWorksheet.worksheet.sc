
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

val e   = Iterator(0)
val r5  = Iterator(1,2,4,2,3,0,1,5,2,3,0,2,1,4,1,3,0,4,5)
val r15 = Iterator(1,2,4,2,3,0,1,5,2,3,0,2,1,4,1,3,0,4,5)

val randomList: List[Iterator[Int]] = List(e,e,e,e,e,r5,e,e,e,e,e,e,e,e,e,r15)

val random:( Int => Int) = k => randomList.apply(k).next()

random(5)

random(5)

random(5)

enum Value {
  case Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine,
        Skip, Reverse, DrawTwo, Wild, WildFour
  override def toString = {
      this match {
        case Zero => " 0" case One => " 1"
        case Two => " 2" case Three=> " 3" case Four => " 4" case Five => " 5"
        case Six=> " 6" case Seven => " 7" case Eight => " 8"  case Nine=> " 9"
        case Skip => "🚫" case Reverse => "🔃" case DrawTwo => "+2"
        case Wild => "🌈" case WildFour => "+4"
      }
  }
}

enum Colour {
  case Red, Green, Blue, Yellow, Black
  override def toString = {
    this match {
      case Red => "🟥"
      case Green => "🟩"
      case Blue => "🟦"
      case Yellow => "🟨"
      case Black => "⬛"
    }
  }
}
var a = List(1,2,3,5)
val b = a.patch(0, Nil, 1)

def createPlayer(players: Int,  deck: Array[(Card)]):List[(Player)]=(0 until players).map(k =>Player(CardDeck.getCardsStack(deck,7).
                                                                        toList,getName())).toList
   def getRandomCard(stack: Array[Card], n: Int):(Card, Array[Card]) =
        val card = stack(n)
        stack.patch(n, Nil, 1)
        (card,stack)

    def getCardsStack(stack: Array[Card], n: Int): (Array[Card], Array[Card]) =
        val newCards = new Array[Card](n)
        var stackClone = stack.clone
        for (i <- 1 to n)
            val tuple = getRandomCard(stackClone, nextInt(stack.length -1))
            newCards(i-1) = tuple._1
            stackClone = tuple._2
        (newCards, stack)