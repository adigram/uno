package  de.htwg.se.uno.model 

trait Card{
    val value: Value
    val colour: Colour

    def action:String = "A Card"

    override def toString = colour.toString + " " + value.toString + " |> " + action
    def equal(cardHand: Card, cardStack: Card): Boolean = cardHand.colour == cardStack.colour || cardHand.value == cardStack.value 
                                                        || cardHand.value == Value.Wild || cardHand.value == Value.WildFour 
}


object Card{
    
    private trait wildDecorator extends Wild{
        def pickColour(c:Colour):Unit
    }
    private trait skipDecorator extends Skip{
        def skipPlayer:Unit
    }
    private trait drawDecorator extends Draw{
        def drawCards:Unit
    }

    private class Generic(value: Value,colour: Colour) extends Card{
        override def action:String = "Normal Card"
    }
    private class Wild extends Card{
        override def action:String = "Wish one Color"
    }
    private class Reverse(colour: Colour) extends Card{
        override def action:String = "Change Direction"
    }
    private class Skip(colour: Colour) extends Card{
        override def action:String = "Skip next Player"
    }
    private class Draw(colour: Colour) extends Card{
        override def action:String = "Draw +x Skip next Player"
    }
    private class WildFour(colour: Colour) extends Card with wildDecorator with drawDecorator{
        override def action:String = {
            super.action +
            "Wish and Draw +x Skip next Player"
        }
    }

    def apply(v: Value, c:Colour): Card = {
        v match
            case Value.Skip => new Skip(c)
            case Value.Wild => new Wild()
            case Value.Reverse => new Reverse(c)
            case Value.DrawTwo => new Draw(c)
            case Value.WildFour => new WildFour(c)
            case _ => new Generic(v,c)
    }
}