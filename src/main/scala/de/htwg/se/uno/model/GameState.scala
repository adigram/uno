package de.htwg.se.uno.model
import scala.io.StdIn.readLine
import de.htwg.se.uno.util.Command
import de.htwg.se.uno.util.Change

case class state (
    currentPlayer:Int,
    players:List[Player],
    direction:Boolean,
    deck:List[Card],
    stack:List[Card],
    event:Event,
    change:Change
    ){
    
    def handle(e:Event):state = {
        e match {
            case e:Event_takeCard   => {takeCardFromDeck().nextPlayer()}    
            //case e:Event_playCard   => playCard(e.chosenCard)
            case e:Event_nextPlayer => nextPlayer()
        }
    }

    def takeCardFromDeck(): state = this //TODO
    def nextPlayer(): state = this //TODO


}