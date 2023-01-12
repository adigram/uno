package de.htwg.se.uno

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model._
import controller._

class ControllerSpec extends AnyWordSpec with Matchers {

    val scan:(Unit => String) = Unit => "TestName"
    val ctrl = new Controller(state(0,List[Player](),true,List[Card](),List(Card(Value.Nine,Colour.Red)),"", true) )
    
    
    "create Players" should{
        
        "return the String" in {
           ctrl.createGame()
           ctrl.createPlayers(List[String]("a", "B"))should be("Players created!\n")
        }
    }

    " the method print players" should{
        "should print the string"in{
        ctrl.State = state(0,List[Player](Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens"),Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"rob") ),true,List[Card](),List(Card(Value.Nine,Colour.Red)),"", true)
        ctrl.printPlayers()
        ctrl.statement should be("Hand Player: Jens\n🟥  9  |  🟩  1\nHand Player: rob\n🟥  9  |  🟩  1\n")
    }

}
    
}
