package de.htwg.se.uno.model
import scala.io.StdIn.readLine
import scala.compiletime.ops.string
import scala.util.Random as random

case class state (
    currentPlayer:Int,
    players:List[Player],
    direction:Boolean,
    deck:List[Card],
    stack:List[Card],
    output: String
    ) extends GameStateInterface{
    
    val bridge: gameBridge = new ConcreteBridge

    def handle(e:Event):state = {
        e match {
            case e:takeCardFromDeckEvent => takeCardFromDeck()                                   
            case e:dropCardEvent         => dropCard(e.chosenCard, e.unoFlag)
            case e:nextPlayerEvent       => nextPlayer()
            case e:dropLastCardEvent     => dropLastCard(e.dropChosenCard, e.unoFlag)
            case e:chooseColourEvent     => chooseColour(e.chooseColour)
            case e:UnoUnoEvent           => UnoUno()
        }
    }
    def this() = this(0,List[Player](), true, List[Card](),List[Card](),"a" )

    def createZeroCards():List[Card]=(0 until 4).map(k =>(Card(Value.values(0), Colour.values(k)))).toList

    def createNormalCards():List[Card]=(0 until 2).flatMap(t =>(0 until 4).
        flatMap(k =>(1 until 13).map(j => (Card(Value.values(j), Colour.values(k)))))).toList

    def wildCards():List[Card] = (0 until 4).flatMap(i =>(13 until 15).
         map(k => Card(Value.values(k), Colour.values(4)))).toList

    def createDeck():state = this.copy( deck =  createZeroCards() ++ createNormalCards() ++ wildCards())

    def takeCard(n: Int): (List[Card] ,List[Card]) = this.deck.splitAt(n)
        
    def createPlayers(Namen:List[String]) = 
        val (player1, deck1) = this.takeCard(7)
        val newState = this.copy(deck = deck1)
        val (player2, deck2) = newState.takeCard(7)
        newState.copy(deck = deck2, players = List(Player(player1, Namen.apply(0)), Player(player2, Namen.apply(1))))
    
    def createGame():state =
        val state1 = this.copy( deck = random.shuffle(this.createDeck().deck))
        val  (stack1, deck1)= state1.takeCard(1)
        state1.copy(stack = stack1, deck = deck1, output = "Start Done")

    def takeCardFromDeck(): state = bridge.takeCardFromDeck(this)
    def nextPlayer(): state = bridge.nextPlayer(this)
    def UnoUno(): state = {
         if(this.players(currentPlayer).hand.length == 1 && this.players(currentPlayer).hand(0).equal(this.stack(0)))
            this.copy(output = "The Player: " + this.players(currentPlayer).name + " won the game!")
         else
            this.copy(output = "You are not able to say Uno Uno\n")
    }

    def dropCard(chosenCard:Option[Int],unoFlag:Boolean): state ={
        chosenCard match {
            case None => this.copy(output = "Your Input wasn'nt a number.\n") 
            case Some(chosenCard) if chosenCard >= 0 && chosenCard < this.players(this.currentPlayer).hand.length =>
                 CardCheck(this.players(this.currentPlayer).hand(chosenCard).equal(this.stack(0)),chosenCard,unoFlag)
            case _  =>    this.copy(output = "The Card you selected doesn't exist in your hand.\n")
            
            
        }  
    }
       
    def CardCheck(input: Boolean, selection: Int, unoFlag:Boolean) : state =  {
        input match {
            case true => return this.dropLastCard(Some(selection), unoFlag)
            case false =>  return this.copy(output = "The Card you selected doesn't doesn't macht with card on the stack.\n") 
        }
    }

    def dropLastCard(chosenCard:Option[Int],unoFlag:Boolean): state ={
        chosenCard match {
            case None => {  
                val cardValue = this.players(this.currentPlayer).hand.last.value
                val cardNumber = this.players(this.currentPlayer).hand.length-1
                cardValue match {
                    case Value.Skip    => this.dropNormalCard(cardNumber, unoFlag).copy(currentPlayer = if(this.direction) (this.currentPlayer + 1)  else (this.currentPlayer - 1)).handle(nextPlayerEvent())
                    case Value.Reverse    => this.dropNormalCard(cardNumber, unoFlag).copy(direction = if(this.direction) false else true).handle(nextPlayerEvent())
                    case Value.DrawTwo    => this.dropNormalCard(cardNumber, unoFlag).handle(nextPlayerEvent()).takeCardFromDeck().takeCardFromDeck()
                    case Value.Wild    => this.dropNormalCard(cardNumber, unoFlag).copy(output = "Which colour do you want?\n0 = red\n1 = green\n2 = blue\n3 = yellow\n")
                    case Value.WildFour    => this.dropNormalCard(cardNumber, unoFlag).handle(nextPlayerEvent()).takeCardFromDeck().takeCardFromDeck().takeCardFromDeck().takeCardFromDeck().copy(output = "Which colour do you want?\n0 = red\n1 = green\n2 = blue\n3 = yellow\n" )
                    case default => this.dropNormalCard(cardNumber, unoFlag).handle(nextPlayerEvent())
                                        }                                                          
                                }   
                          
            case Some(chosenCard) => {
                 val cardValue = this.players(this.currentPlayer).hand(chosenCard).value
                 cardValue match {
                    case Value.Skip    => this.dropNormalCard(chosenCard, unoFlag).copy(currentPlayer = if(this.direction) (this.currentPlayer + 1)  else (this.currentPlayer - 1)).handle(nextPlayerEvent())
                    case Value.Reverse    => this.dropNormalCard(chosenCard, unoFlag).copy(direction = if(this.direction) false else true).handle(nextPlayerEvent())
                    case Value.DrawTwo     => this.dropNormalCard(chosenCard,unoFlag).handle(nextPlayerEvent()).takeCardFromDeck().takeCardFromDeck()
                    case Value.Wild    => this.dropNormalCard(chosenCard, unoFlag).nextPlayer().copy(output = "Which colour do you want?\n0 = red\n1 = green\n2 = blue\n3 = yellow\n")
                    case Value.WildFour    => this.dropNormalCard(chosenCard,unoFlag).nextPlayer().takeCardFromDeck().takeCardFromDeck().takeCardFromDeck().takeCardFromDeck().copy(output = "Which colour do you want?\n0 = red\n1 = green\n2 = blue\n3 = yellow\n" )
                    case default => this.dropNormalCard(chosenCard,unoFlag).handle(nextPlayerEvent()) 
                         }  
            
                }
            }
        }
    
    
    def dropNormalCard(CardNumber: Int, unoFlag:Boolean): state = {
        val hand = this.players(this.currentPlayer).hand
        val stack = List(hand(CardNumber)) ++ this.stack
        val playerCurrent = Player(this.players(this.currentPlayer).hand.zipWithIndex.filter(_._2 != CardNumber).map(_._1),this.players(this.currentPlayer).name)
        val players = this.players.updated(this.currentPlayer, playerCurrent)
        if(playerCurrent.hand.length == 1 && unoFlag == false) 
            this.copy(players = players, stack = stack).takeCardFromDeck()
        else this.copy(players = players, stack = stack)
    }

    def chooseColour(colourNumber: Option[Int]): state ={ 
        colourNumber match{
            case Some(colourNumber) => this.copy(stack = List(Card(Value.values(0),Colour.values(colourNumber))) ++ this.stack,
                                                        output = "Now it's the round of:\n" + this.players(this.currentPlayer).toString + "\n" + 
                                                        Card(Value.values(0),Colour.values(colourNumber)).toString() +"\n")
            case none => this.copy(output = "You didn't choose a Colour.The will be the same as before.\n")
        }
    }


}