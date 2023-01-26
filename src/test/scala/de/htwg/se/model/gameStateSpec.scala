package de.htwg.se.model
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.uno.model.*
import de.htwg.se.uno.controller._


class gameStateSpec extends AnyWordSpec with Matchers {

    val simState = state(0,List[Player](Player(List(Card(Value.Nine,Colour.Red),Card(Value.One,Colour.Red)),"Jens"),
    Player(List(Card(Value.Seven,Colour.Red),Card(Value.One,Colour.Blue)),"rob") ),
    true,List(Card(Value.One,Colour.Red), Card(Value.Three,Colour.Red),Card(Value.One,Colour.Red),
    Card(Value.Two,Colour.Blue), Card(Value.Nine,Colour.Red),Card(Value.Two,Colour.Blue)),List(Card(Value.Nine,Colour.Red)),"jaaa", true)
    
    "create Players" should{
        "return the String" in {
           var newState = simState.takeCardFromDeck(1) 
           newState.deck.length should be (5)
           newState = simState.takeCardFromDeck(2)
           newState.players(0).hand.length should be (4)
        }
    }

    "next Players" should{
        "set Currentplayer" in {
           var newState1 = simState.nextPlayer()
           newState1.currentPlayer should be (1)
        }
    }

    "UNO UNO" should{
        "set Currentplayer" in {
           var newState2 = simState.UnoUno()
           newState2.output should be ("You are not able to say Uno Uno\n")
           newState2 = state(0,List[Player](Player(List(Card(Value.Nine,Colour.Red)),"Jens")),
                            true,List(Card(Value.Two,Colour.Blue)),List(Card(Value.Nine,Colour.Red)),"jaaa", true)
           newState2 = newState2.UnoUno()
           newState2.output should be ("The Player: " + "Jens" + " won the game!")
        }
    }
  
     "Choose colour" should{
        "set Currentplayer" in {
           var newState3 = simState.chooseColour(None)
           newState3.output should be("You didn't choose a Colour: picked Red for you\n")
           newState3 = simState.chooseColour(Option(1))
           newState3.stack(0) should be (Card(Value.Wild,Colour.Green))
    }
}

     "drop card" should{
        "set Currentplayer" in {
           var stateNew = simState.dropCard(None)
           stateNew.output should be("Your Input wasn'nt a number.\n")
           stateNew = simState.dropCard(Option(0))
           stateNew.stack(0) should be (Card(Value.Nine,Colour.Red))
           
    }
}

     "drop last card" should{
        "set Currentplayer" in {
           var stateNew = simState.dropLastCard(None)
           stateNew.stack(0) should be (Card(Value.One,Colour.Red))
           
    }
}




}
