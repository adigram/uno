package  de.htwg.se.uno.model 

 case class Card(value: Value, colour: Colour){
    override def toString = colour.toString + " " + value.toString
    def equal( cardStack: Card): Boolean = this.colour == cardStack.colour || this.value == cardStack.value || this.value == Value.Wild || this.value == Value.WildFour 
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
