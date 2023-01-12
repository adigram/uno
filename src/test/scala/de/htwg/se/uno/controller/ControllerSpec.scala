package de.htwg.se.uno.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.uno.model.*
import de.htwg.se.uno.controller._
import com.google.inject.{Guice, Inject}
import de.htwg.se.uno.IOXML
import de.htwg.se.uno.IOJSON

class ControllerSpec extends AnyWordSpec with Matchers {

    val simState = state(0,List[Player](Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens"),
    Player(List(Card(Value.Seven,Colour.Red),Card(Value.One,Colour.Blue)),"rob") ),
    true,List(Card(Value.Two,Colour.Red), Card(Value.Three,Colour.Red),Card(Value.One,Colour.Red),
    Card(Value.Two,Colour.Blue), Card(Value.Nine,Colour.Red),Card(Value.Two,Colour.Blue)),List(Card(Value.Nine,Colour.Red)),"jaaa", true)

    val scan:(Unit => String) = Unit => "TestName"
    val ctrl = new Controller(simState)
    
    
    "create Players" should{
        
        "return the String" in {
           ctrl.createGame()
           ctrl.createPlayers(List[String]("a", "B"))should be("Players created!\n")
        }
    }

    "the method print players" should{
        "should print the string"in{
        ctrl.State = state(0,List[Player](Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"Jens"),Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Green)),"rob") ),true,List[Card](),List(Card(Value.Nine,Colour.Red)),"", true)
        ctrl.printPlayers()
        ctrl.statement should be("Hand Player: Jens\n🟥  9  |  🟩  1\nHand Player: rob\n🟥  9  |  🟩  1\n")
    }

}

    "The method doStep" should{
        "should do" in {
            ctrl.State = simState
            val newState = ctrl.doStep(UnoUnoEvent()) 
            assert(newState.isInstanceOf[GameStateInterface])
            ctrl.undo()
            ctrl.State should be(simState)
            ctrl.redo()
            ctrl.State should be (newState)
        }
    }

    "The method load JSON" should{
        "should do" in {
            ctrl.State = simState
            val newState = ctrl.save 
            ctrl.statement should be("Save Done")
            ctrl.doStep(UnoUnoEvent())
            ctrl.load
            ctrl.State should be (simState)
        }
    }

    "The method load XML" should{
        "should do" in {
            ctrl.State = simState
            ctrl.fileIO = Guice.createInjector(new IOXML).getInstance(classOf[FileIOInterface])
            val newState = ctrl.save 
            ctrl.statement should be("Save Done")
            ctrl.doStep(UnoUnoEvent())
            ctrl.load
            ctrl.State should be (simState)
        }
    }

    "The method printFirstCard" should{
        "should do" in {
            ctrl.State = simState
            ctrl.printFirstcard() should be("Stack: " + ctrl.State.stack(0).toString)
        }
    }

    
}
