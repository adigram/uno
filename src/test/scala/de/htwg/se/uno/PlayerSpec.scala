package de.htwg.se.uno

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model._



class PlayerSpec extends AnyWordSpec with Matchers {

    "A Player" when {
        val player = Player(List(Card(Value.Nine,Colour.Red,1),Card(Value.One,Colour.Green,2)),"Jens")
    "when its created" should{
        "have its Hand" in {
        player.hand should be(List(Card(Value.Nine,Colour.Red,1),Card(Value.One,Colour.Green,2)))
      }
      "have the name" in {
        player.name should be("Jens")
      }
    }
    "when its printed" should {
      "is printed" in {
        player.toString should be("Hand Player: Jens\n🟥  9  |  🟩  1\n")
      }
   }
 }
}
  

