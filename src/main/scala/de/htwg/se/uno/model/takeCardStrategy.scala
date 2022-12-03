package de.htwg.se.uno.model
import scala.util.Random as r 

object takeCardStrategy{

    def apply(State: state) : (List[Player], List[Card], List[Card]) = State.deck.length match{
        case x if x > 50  =>  stackBiggerFive(State)
        case default =>  stackSmallerSix(State)
    }
   
    def stackBiggerFive(State: state):(List[Player],List[Card],List[Card])={
        val (card,deck) = State.deck.splitAt(1)
        val players = State.players.updated(State.currentPlayer, Player(State.players(State.currentPlayer).hand ++ card,
                                            State.players(State.currentPlayer).name))
        
        
        (players, deck, State.stack)
    }

    def stackSmallerSix(State: state):(List[Player], List[Card],List[Card])={
        val (newStack,oldStack) = State.stack.splitAt(1)
        val newDeck= r.shuffle((CardDeck.deck ++ oldStack))
        val stack = newStack
        return stackBiggerFive(state(State.currentPlayer,State.players, State.direction,newDeck, stack,"" ))
    }
       
}
