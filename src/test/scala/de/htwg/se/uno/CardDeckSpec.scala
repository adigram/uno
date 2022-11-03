package de.htwg.se.uno
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model._
import scala.util.Random as r 

class CardDeckSpec extends AnyWordSpec with Matchers{

    "take Card" should{
        "split the deck" in {
            CardDeck.takeCard(2) should be(List(Card(Value.Zero,Colour.Red,0),Card(Value.Zero,Colour.Green,1)))
        }
    }
    "shuffle" should{
        CardDeck.deck = CardDeck.createDeck()
        "shuffle the deck" in {
            val oldDeck = CardDeck.deck
            CardDeck.shuffle(r)
            CardDeck.deck should not be(oldDeck)
        }   
    }
    
}
