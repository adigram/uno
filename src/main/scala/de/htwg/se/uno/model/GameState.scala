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
            case takeCard: takeCardFromDeckEvent => takeCardFromDeck()    
            case drop: dropCardEvent => dropCard()
        }
    }

    
    
    def takeCardFromDeck(): state = bridge.takeCardFromDeck(this)
    def setCurrentPlayer(State : state ): Int = bridge.setCurrentPlayer(this)

    def dropCard(): state ={
       toInt(readLine()) match {
            case x if x >= 0 && x < this.players(this.currentPlayer).hand.length => CardCheck(this.players(this.currentPlayer).hand(x).equal(this.stack(0)),this,this.players(this.currentPlayer).hand(x))
            case default => this.copy(output = "The Card you selected doesn't exist in your hand.\n") 

        }
        
    }
       
    def CardCheck(input: Boolean, State: state, selection: Card) : state =  {
        input match {
            case true => CardBehavior(State, selection)
            case false =>  return State.copy(output = "The Card you selected doesn't doesn't macht with card on the stack.\n") 
        }
    }

    def CardBehavior(State:state, selection: Card): state = {
        selection.value match {
            case Value.Skip => skip.action(State)
            case Value.Reverse =>
            case Value.DrawTwo =>
            case Value.Wild =>
            case Value.WildFour =>
            case default =>
        }

    }

     def toInt(s: String): Int = {
         try {
            s.toInt
         } 
         catch {
            case e: Exception => -1
         }
    }
 }



  

    