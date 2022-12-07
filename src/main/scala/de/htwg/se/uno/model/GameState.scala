package de.htwg.se.uno.model
import scala.io.StdIn.readLine
import scala.compiletime.ops.string

case class state (
    currentPlayer:Int,
    players:List[Player],
    direction:Boolean,
    deck:List[Card],
    stack:List[Card],
    output: String,
    event: Event
    ){
    
    def handle(e:Event):state = {
        e match {
            case e:Event_takeCard   => {takeCardFromDeck().nextPlayer()}    
            case e:Event_dropCard   => dropCard(e.chosenCard)
            case e:Event_nextPlayer => nextPlayer()
        }
    }

    def takeCardFromDeck(): state = this //TODO
    def nextPlayer(): state = this //TODO

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