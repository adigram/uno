package de.htwg.se.uno

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import view._

class TUISpec extends AnyWordSpec with Matchers {
    val tui = TUI()
    tui.scan = Unit => "TestName"

    "TUI" should {
        "create Players" in {
            tui.createPlayers(6).length should be(6)
        }
    }
}
