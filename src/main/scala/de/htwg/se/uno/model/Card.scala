package  de.htwg.se.uno.model 
 
final case class Card(value: Value, colour: Colour, id: Int){
    override def toString = colour.toString + " " + value.toString
    def equal(cardHand: Card, cardStack: Card): Boolean = cardHand.colour == cardStack.colour || cardHand.value == cardStack.value 
                                                        || cardHand.value == Value.Wild || cardHand.value == Value.WildFour 
}

