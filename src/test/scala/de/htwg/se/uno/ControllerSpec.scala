package de.htwg.se.uno

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import controller._

class ControllerSpec extends AnyWordSpec with Matchers {

    val scan:(Unit => String) = Unit => "TestName"
    val ctrl = new Controller 
    "Controller" should {
        "create Players" in {
            ctrl.createPlayers(6,scan)
            ctrl.players.length should be(6)
        }
    }
}
