package  de.htwg.se.uno.model 
import scala.util.Random
import scala.collection.mutable.ListBuffer
object CardDeck {

    var deck = createDeck()
    
    def createZeroCards():List[Card]=(0 until 4).map(k =>(Card(Value.values(0), Colour.values(k),k))).toList

    def createNormalCards():List[Card]=(0 until 2).flatMap(t =>(0 until 4).
    flatMap(k =>(1 until 13).map(j => (Card(Value.values(j), Colour.values(k),k))))).toList

    def wildCards():List[Card] = (0 until 4).flatMap(i =>(13 until 15).
    map(k => Card(Value.values(k), Colour.values(4),k))).toList

    def createDeck():List[Card] = createZeroCards() ++ createNormalCards() ++ wildCards()

    def shuffle(r: scala.util.Random) = this.deck = r.shuffle(CardDeck.deck)

    def takeCard(n: Int)=  {var split = CardDeck.deck.splitAt(n); this.deck = split._2; split._1}

}