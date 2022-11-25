package de.htwg.se.uno
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model._
import scala.util.Random as r 

class CardDeckSpec extends AnyWordSpec with Matchers{

    "take Card" should{
        "split the deck" in {
            CardDeck.takeCard(2) should not be(List(Card(Value.Five,Colour.Blue),Card(Value.Three,Colour.Blue)))
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
