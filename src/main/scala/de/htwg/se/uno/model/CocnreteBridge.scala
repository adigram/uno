package de.htwg.se.uno.model

class ConcreteBridge extends gameBridge{
    def takeCardFromDeck(State:state): state = {
        val(player, newDeck, newStack) = takeCardStrategy.apply(State)
        val currentPlayer = State.setCurrentPlayer(State)
        val output = player(State.currentPlayer).toString() + "\nNow its the round of: " + State.players(currentPlayer).toString
        return state(currentPlayer, player, State.direction, newDeck, newStack, output)
    }
    def setCurrentPlayer(State : state ): Int ={
        State.direction match {   
            case true => State.currentPlayer % State.players.length
            case false => (State.currentPlayer-2) % State.players.length
         }
         
     }
  
}
