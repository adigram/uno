package de.htwg.se.uno.model

class ConcreteBridge extends gameBridge{
    def takeCardFromDeck(State:state): state = {
        val(player, newDeck, newStack) = takeCardStrategy.apply(State)
        val output = player(State.currentPlayer).toString() 
        return state(State.currentPlayer, player, State.direction, newDeck, newStack, output)
    }
    def nextPlayer(State : state ): state ={
        printf(State.direction.toString)
        val newPlayer = State.direction match {   
            case true =>  (State.currentPlayer +1) % State.players.length
            case false => {
                val player = (State.currentPlayer-1) % State.players.length
                if(player == -1) State.players.length-1 else player 
            }
        }
        State.copy(currentPlayer = newPlayer, output = "\nNow its the round of: \n" + State.players(newPlayer).toString + "Stack: " + State.stack(0).toString + "\n")
     }
  
}
