package de.htwg.se.uno.model.gamestate

import de.htwg.se.uno.model._

trait GameState{
    val currentPlayer:Int
    val players:List[Player]
    val direction:Boolean
    val deck:List[Card]
    val stack:List[Card]

    def handle(e:Event):state
}