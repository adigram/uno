package  de.htwg.se.uno.model 

final case class Card(value: Value, colour: Colour){
    override def toString = colour.toString + " " + value.toString
    def equal( cardStack: Card): Boolean = this.colour == cardStack.colour || this.value == cardStack.value || this.value == Value.Wild || this.value == Value.WildFour 
                                                        
}

