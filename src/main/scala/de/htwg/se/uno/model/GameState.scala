package de.htwg.se.uno.model
import scala.io.StdIn.readLine
import scala.compiletime.ops.string

case class state (
    currentPlayer:Int,
    players:List[Player],
    direction:Boolean,
    deck:List[Card],
    stack:List[Card],
    output: String
    ){
    
    val bridge: gameBridge = new ConcreteBridge

    def handle(e:Event):state = {
        e match {
            case e:takeCardFromDeckEvent => {takeCardFromDeck().nextPlayer()}    
            case e:dropCardEvent         => dropCard(e.chosenCard)
            case e:nextPlayerEvent       => nextPlayer()
        }
    }

    def takeCardFromDeck(): state = bridge.takeCardFromDeck(this)
    def nextPlayer(): state = bridge.nextPlayer(this)

    def dropCard(chosenCard:Int): state ={
        chosenCard match {
            case x if x >= 0 && x < this.players(this.currentPlayer).hand.length => CardCheck(this.players(this.currentPlayer).hand(x).equal(this.stack(0)),this.players(this.currentPlayer).hand(x))
            case default => this.copy(output = "The Card you selected doesn't exist in your hand.\n") 
        }  
    }
       
    def CardCheck(input: Boolean, selection: Card) : state =  {
        input match {
            case true => return this.copy(output = "Dropped Card.\n")
            case false =>  return this.copy(output = "The Card you selected doesn't doesn't macht with card on the stack.\n") 
        }
    }

}