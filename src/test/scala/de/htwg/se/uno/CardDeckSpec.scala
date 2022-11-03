package de.htwg.se.uno
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model._
import scala.util.Random as r 

class CardDeckSpec extends AnyWordSpec with Matchers{
    "Zero" when {
            val zeroCards = CardDeck.createZeroCards()
        "when its created" should{
            "have the value" in {
            zeroCards should be(List(Card(Value.Zero,Colour.Red,0),Card(Value.Zero,Colour.Green,1),
            Card(Value.Zero,Colour.Blue,2),Card(Value.Zero,Colour.Yellow,3)))     
                }
        "take Card" should{
            "split the deck" in {
                CardDeck.takeCard(2) should be(List(Card(Value.Zero,Colour.Red,0),Card(Value.Zero,Colour.Green,1)))
                    }
                }
        "shuffle" should{
            "shuffle the deck" in {
                CardDeck.shuffle(r) should not be(CardDeck.shuffle(r))
                }   
             }
        }
    }
}