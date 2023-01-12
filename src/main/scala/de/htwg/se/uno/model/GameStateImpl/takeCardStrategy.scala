package de.htwg.se.uno.model
import scala.util.Random as r 

object takeCardStrategy{

    def apply(State: state,  anzahl: Int) : (List[Player], List[Card], List[Card]) = State.deck.length match{
        case x if x > 50  =>  stackBiggerFive(State, anzahl)
        case default =>  stackSmallerSix(State, anzahl)
    }
   
    def stackBiggerFive(State: state,  anzahl: Int):(List[Player],List[Card],List[Card])={
        val (card,deck) = State.deck.splitAt(anzahl)
        val players = State.players.updated(State.currentPlayer, Player(State.players(State.currentPlayer).hand ++ card,
                                            State.players(State.currentPlayer).name))
        
        printf(State.currentPlayer.toString())
        (players, deck, State.stack)
    }

    def stackSmallerSix(State: state, anzahl: Int):(List[Player], List[Card],List[Card])={
        val (newStack,oldStack) = State.stack.splitAt(anzahl)
        val newDeck= r.shuffle((State.deck ++ oldStack))
        val stack = newStack
        return stackBiggerFive(state(State.currentPlayer,State.players, State.direction,newDeck, stack,"",false ), anzahl)
    }
       
}
