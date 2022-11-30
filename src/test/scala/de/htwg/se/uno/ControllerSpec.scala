package de.htwg.se.uno

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model._
import controller._

class ControllerSpec extends AnyWordSpec with Matchers {

    val scan:(Unit => String) = Unit => "TestName"
    val ctrl = new Controller
    ctrl.State = state(0,List[Player](),true,List[Card](),List(Card(Value.Nine,Colour.Red)),"") 
    "Controller" should {
        "create Players" in {
            ctrl.createPlayers(0,scan)
            
        }
    }
    "printFirstcard" should{
        "return the String" in {
            ctrl.printFirstcard() should be("Stack: 🟥  9")
        }
    }
    
    "handle" should{
        "return the String" in {
            ctrl.State = ctrl.State.copy(players = List(Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens"),Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens")))
            ctrl.doStep(takeCardFromDeckEvent()) should be("\nNow its the round of: Hand Player: Jens\n🟥  9  |  🟩  1\n")
        }
    }

    "handleSmallerSix" should{
        "return the String" in {
            ctrl.State = ctrl.State.copy(deck = List(Card(Value.Nine,Colour.Red)),players = List(Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens"),Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens")))
            ctrl.doStep(takeCardFromDeckEvent()) should be("\nNow its the round of: Hand Player: Jens\n🟥  9  |  🟩  1\n")
        }
    }


    "handleDropCard" should{
        "return the String" in {
            ctrl.State = ctrl.State.copy(deck = List(Card(Value.Nine,Colour.Red)),players = List(Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens"),Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens")))
            ctrl.doStep(dropCardEvent(10)) should be("The Card you selected doesn't exist in your hand.\n")
        }
    }

    "handleDropCardWrong" should{
        "return the String" in {
            ctrl.State = ctrl.State.copy(deck = List(Card(Value.Nine,Colour.Red)),players = List(Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens"),Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens")))
            ctrl.doStep(dropCardEvent(1)) should be("The Card you selected doesn't doesn't macht with card on the stack.\n")
        }
    }
}
