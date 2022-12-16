package de.htwg.se.uno.controller

import de.htwg.se.uno.util._
import de.htwg.se.uno.model._
import scala.util.Random as random 
import de.htwg.se.uno.uno

object Controller extends Observable{

    var State = state(0,List[Player](),true,List[Card](),List[Card](),Event_createGame(),SetupPlayers())

    def createGame() =
        CardDeck.shuffle(random)
        State = State.copy(stack = (CardDeck.takeCard(1)))
        notifyObservers(SetupPlayers())
        
    def createPlayers(amount: Int,names: List[String]) = 
        State = State.copy(deck = CardDeck.deck, players = (0 until amount).map(k =>Player(CardDeck.takeCard(7),names(k))).toList )
        notifyObservers(PlayerHands(State.players))
        notifyObservers(TopCard(State.stack(0)))

    def handle(event: Event) = {
        State = State.handle(event)
        notifyObservers(State.change)
    }

    def request(command:Command) = {
        command match
            case command : ResponseSetupPlayers => createPlayers(command.amount,command.names)
            case _                              => println("Unknow Command")
    }

}   
