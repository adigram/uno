package de.htwg.se.uno

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model._
import controller._

class ControllerSpec extends AnyWordSpec with Matchers {

    val scan:(Unit => String) = Unit => "TestName"
    val ctrl = new Controller(state(0,List[Player](),true,List[Card](),List(Card(Value.Nine,Colour.Red)),"", true) )
    
    
    "create Players" should{
        ctrl.createGame()
        "return the String" in {
           ctrl.createPlayers(List[String]("a, B"))should be("Players created!\n")
        }
    }

   
    
}
