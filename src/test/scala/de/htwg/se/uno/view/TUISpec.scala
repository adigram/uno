/*package de.htwg.se.uno.view
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

        "case default" in {
            tui.input("paa")
            ctrl.State.unoFlag should be (true)
        }

        "case start" in {
            tui.input("paa")
            ctrl.State.unoFlag should be (true)
            ctrl.createGame()
            tui.input("a b")
            ctrl.State.players(0).name should be("a")
            ctrl.State = state(0,List[Player](Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens"),Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"rob") ),true,List[Card](Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green),Card(Value.Nine,Colour.Blue),Card(Value.Two,Colour.Green),Card(Value.Eight,Colour.Blue),Card(Value.Two,Colour.Yellow)),List(Card(Value.Nine,Colour.Red)),"", true)
            ctrl.printPlayers()
            tui.input("undo")
            tui.flagInput should be (1)
            tui.input("redo")
            tui.flagInput should be (1)
            tui.input("save")
            tui.flagInput should be (1)
            tui.input("load")
            tui.flagInput should be (1)
            
        }

        "case default in if" in {
            ctrl.createGame()
            ctrl.createPlayers(List[String]("a","b"))
            tui.input("paa")
            ctrl.State.unoFlag should be (true)
        }
}
}

*/