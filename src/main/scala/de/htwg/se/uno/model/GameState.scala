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
            case e:takeCardFromDeckEvent => {
                                             val state = takeCardFromDeck()
                                             if(state.players(state.currentPlayer).hand.last.equal(state.stack(0)))
                                                state.copy(output =  state.output + "Do you wan't to drop the Card you took from the stack?[y/n]\n")
                                             else
                                                state.handle(nextPlayerEvent()) 
                                            }    
            case e:dropCardEvent         => dropCard(e.chosenCard)
            case e:nextPlayerEvent       => nextPlayer()
            case e:dropLastCardEvent     => dropLastCard(e.dropChosenCard)
        }
    }

    def takeCardFromDeck(): state = bridge.takeCardFromDeck(this)
    def nextPlayer(): state = bridge.nextPlayer(this)

    def dropCard(chosenCard:Option[Int]): state ={
        chosenCard match {
            case None => this.copy(output = "Your Input wasn'nt a number.\n") 
            case Some(chosenCard) if chosenCard >= 0 && chosenCard < this.players(this.currentPlayer).hand.length =>
                 CardCheck(this.players(this.currentPlayer).hand(chosenCard).equal(this.stack(0)),chosenCard)
            case _  =>    this.copy(output = "The Card you selected doesn't exist in your hand.\n")
            
            
        }  
    }
       
    def CardCheck(input: Boolean, selection: Int) : state =  {
        input match {
            case true => return this.dropLastCard(Some(selection))
            case false =>  return this.copy(output = "The Card you selected doesn't doesn't macht with card on the stack.\n") 
        }
    }

    def dropLastCard(chosenCard:Option[Int]): state ={
        chosenCard match {
            case None => {  
                val cardValue = this.players(this.currentPlayer).hand.last.value
                val cardNumber = this.players(this.currentPlayer).hand.length-1
                cardValue.toString match {
                    //case Value.Skip     =>
                    case "🔃"            => this.dropNormalCard(cardNumber).copy(direction = if(this.direction) false else true).handle(nextPlayerEvent())
                    //case Value.DrawTwo  =>
                    //case Value.Wild     =>
                    //case Value.WildFour =>
                    case default        => this.dropNormalCard(cardNumber).handle(nextPlayerEvent())
                                        }                                                          
                                }   
                          
            case Some(chosenCard) => {
                 val cardValue = this.players(this.currentPlayer).hand(chosenCard).value
                 cardValue.toString match {
                    case "🔃"            => this.dropNormalCard(chosenCard).copy(direction = if(this.direction) false else true).handle(nextPlayerEvent())
                            
                    case default         =>     this.dropNormalCard(chosenCard).handle(nextPlayerEvent()) 
                         }  
            
                }
            }
        }
    
    
    def dropNormalCard(CardNumber: Int): state = {
        val hand = this.players(this.currentPlayer).hand
        val stack = List(hand(CardNumber)) ++ this.stack
        val playerCurrent = Player(this.players(this.currentPlayer).hand.zipWithIndex.filter(_._2 != CardNumber).map(_._1),this.players(this.currentPlayer).name)
        val players = this.players.updated(this.currentPlayer, playerCurrent)
        this.copy(players = players, stack = stack)

    }

}