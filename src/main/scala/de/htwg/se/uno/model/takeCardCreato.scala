package de.htwg.se.uno.model
import de.htwg.se.uno.uno
import scala.util.Random

trait takeCardCreato{
    def takeCardFromStack(): String
}
object takeCardCreato {
  def apply(stack: List[(Card)]) = stack.length match{
    case x if x > 5  => new stackBiggerFive().takeCardFromStack()
    case default => new stackSmallerSix().takeCardFromStack()
  }
}

private class stackBiggerFive extends takeCardCreato{
    override def takeCardFromStack(): String = { 
        uno.controller.players = uno.controller.players.updated(uno.controller.game.currentPlayer,
                                  Player((uno.controller.players(uno.controller.game.currentPlayer).hand ++ CardDeck.takeCard(1)),
                                            uno.controller.players(uno.controller.game.currentPlayer).name))
        return uno.controller.players(uno.controller.game.currentPlayer).toString
    }
}


private class stackSmallerSix extends takeCardCreato{
    override def takeCardFromStack(): String = { 
        val (newStack,oldStack) = CardDeck.stack.splitAt(1)
        CardDeck.deck = CardDeck.deck ++ oldStack
        CardDeck.shuffle(scala.util.Random)
        CardDeck.stack = newStack
        return stackBiggerFive().takeCardFromStack()
    }
}