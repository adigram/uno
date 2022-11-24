package de.htwg.se.uno.model

 case class CardFunktion(){
    def action:Unit = println("test")
}

    trait wild extends CardFunktion{
        def pickColour(c:Colour):String = "Wild Card"
        override def action:Unit = {
            super.action;
            println(pickColour(Colour.Red)) //todo no parameter
        }
    }
    trait skip extends CardFunktion{
        def skipPlayer:String = "Skip Card"
        override def action:Unit = {
            super.action;
            println(skipPlayer)
        }
    }
    trait draw(amount:Int) extends CardFunktion{
        def drawCards:String = "Draw "+amount+" Cards"
        override def action:Unit = {
            super.action;
            println(drawCards)
        }
    }
    trait reverse() extends CardFunktion{
        def reverse:String = "Reverse"
        override def action:Unit = {
            super.action;
            println(reverse)
        }
    } 
     


