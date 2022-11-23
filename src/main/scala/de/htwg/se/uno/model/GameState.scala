package de.htwg.se.uno.model

case class state (
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
        this.copy(currentPlayer = this.currentPlayer + 1) //todo change
    }
      
}
  

