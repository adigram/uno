package de.htwg.se.uno.model

class state (
    currentPlayer:Int,
    players:List[Player],
    direction:Boolean,
    deck:List[Card],
    stack:List[Card]){

    def handle(e:Event):state = {
        e match {
            case wrongInstructionEvent => {println("Erneut eingeben");this}
            case callUno => {println("Uno");this}
            case callUnoUno => {println("Uno Uno");this}
            case nextPlayerEvent => nextPlayer()
        }
    }

    def nextPlayer():state = {
        new state(this.currentPlayer + 1,this.players,this.direction,this.deck,this.stack)
    }
    
   
}
  

