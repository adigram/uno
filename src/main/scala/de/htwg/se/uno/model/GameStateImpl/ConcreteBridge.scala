package de.htwg.se.uno.model
import de.htwg.se.uno.util._

class ConcreteBridge extends gameBridge{
    def takeCardFromDeck(State:state): state = {
        val(player, newDeck, newStack) = takeCardStrategy.apply(State)
        if(player(State.currentPlayer).hand.last.equal(newStack(0)))
            return state(State.currentPlayer, player, State.direction, newDeck, newStack, "Do you wan't to drop the Card you took from the stack?[y/n]\n", Trigger.dropAftertake)
        else
           val nextPlayerState = State.nextPlayer()
           return state(nextPlayerState.currentPlayer, player, State.direction, newDeck, newStack, nextPlayerState.output, Trigger.print)
        
    }
    def nextPlayer(State : state ): state ={
        val newPlayer = State.direction match {   
            case true =>  (State.currentPlayer +1) % State.players.length
            case false => {
                val player = (State.currentPlayer-1) % State.players.length
                if(player == -2) State.players.length-2 else if(player == -1) State.players.length-1 else player
            }
        }
        State.copy(currentPlayer = newPlayer, output = "\nNow its the round of: \n" + State.players(newPlayer).toString + "Stack: " + State.stack(0).toString + "\n")
     }
  
}
