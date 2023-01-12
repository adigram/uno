package de.htwg.se.uno.view
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.uno.view
import de.htwg.se.uno.controller._
import de.htwg.se.uno.model._
class TUISpec extends AnyWordSpec with Matchers{
    "The function toInt" when {
        val ctrl = new Controller(state(0,List[Player](),true,List[Card](),List(Card(Value.Nine,Colour.Red)),"", true))
        val tui = TUI(ctrl)
        "when its called" should {
        "have Value one" in {
            tui.toInt("1") should be(Some(1))
        }
        
        }
        "when its called with wrong" should {
        "have Value -1" in {
            tui.toInt("w") should be(None)
        }
        
        }
}
}

