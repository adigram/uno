package de.htwg.se.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.uno.model.*

class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    val player = Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens")
    "when its created" should{
      "have its Hand" in {
        player.hand should be(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)))
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