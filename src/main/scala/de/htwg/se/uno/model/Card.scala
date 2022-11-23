package  de.htwg.se.uno.model 

// trait Card{
//     val value: Value
//     val colour: Colour

//     def action:String = "A Card"

//     override def toString = colour.toString + " " + value.toString + " |> " + action
//     def equal(cardHand: Card, cardStack: Card): Boolean = cardHand.colour == cardStack.colour || cardHand.value == cardStack.value 
//                                                         || cardHand.value == Value.Wild || cardHand.value == Value.WildFour 
// }
// final case class Card(value: Value, colour: Colour){
//     override def toString = colour.toString + " " + value.toString
//     def equal(cardHand: Card, cardStack: Card): Boolean = cardHand.colour == cardStack.colour || cardHand.value == cardStack.value 
//                                                         || cardHand.value == Value.Wild || cardHand.value == Value.WildFour 
// }

case class Card(){
    def action:Unit = println("test")
}

    trait wild extends Card{
        def pickColour(c:Colour):String = "Wild Card"
        override def action:Unit = {
            super.action;
            println(pickColour(Colour.Red)) //todo no parameter
        }
    }
    trait skip extends Card{
        def skipPlayer:String = "Skip Card"
        override def action:Unit = {
            super.action;
            println(skipPlayer)
        }
    }
    trait draw(amount:Int) extends Card{
        def drawCards:String = "Draw "+amount+" Cards"
        override def action:Unit = {
            super.action;
            println(drawCards)
        }
    }
    trait reverse() extends Card{
        def reverse:String = "Reverse"
        override def action:Unit = {
            super.action;
            println(reverse)
        }
    }

    // class Generic(value: Value,colour: Colour) extends Card{
    //     override def action:String = "Normal Card"
    // }
    // class Wild extends Card{
    //     override def action:String = "Wish one Color"
    // }
    // class Reverse(colour: Colour) extends Card{
    //     override def action:String = "Change Direction"
    // }
    // class Skip(colour: Colour) extends Card{
    //     override def action:String = "Skip next Player"
    // }
    // class Draw(colour: Colour) extends Card{
    //     override def action:String = "Draw +x Skip next Player"
    // }
    // class WildFour(colour: Colour) extends Card with wild with draw{
    //     override def action:String = {
    //         super.action +
    //         "Wish and Draw +x Skip next Player"
    //     }
    // }