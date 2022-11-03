// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package de.htwg.se.uno

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model._

val e   = Iterator(0)
val r5  = Iterator(3,2,4,2,3,0,1,4,2,3,0,2,1,4,1,3,0,4,2,0,3,2,1,2,4,3)
val r15 = Iterator(8,14,14,11,7,3,8,5,9,3,12,2,1,4,6,1,3,10,4,9,5,1,13,10,6,7,3,4)

val randomList: List[Iterator[Int]] = List(e,e,e,e,e,r5,e,e,e,e,e,e,e,e,e,r15)
val random:( Int => Int) = k => randomList.apply(k).next()

class CardSpec extends AnyWordSpec with Matchers {

   "A Card" when {
    val card = Card(Value.Nine, Colour.Red, 14)
    "when its created" should {
      "have Value Nine" in {
        card.value should be(Value.Nine)
      }
      "have pictrue ACE" in {
        card.colour should be(Colour.Red)
      }
    }
    "when its printed" should {
      "is printed" in {
        card.toString should be("🟥  9")
      }
    }
    "when its equal" should {
      "be true" in{ 
        card.equal(card,Card(Value.Nine, Colour.Red, 14) ) should be (true)
      }
    }
    }
  }
  
