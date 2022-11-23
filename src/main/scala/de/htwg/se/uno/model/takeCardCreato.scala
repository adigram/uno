package de.htwg.se.uno.model
import de.htwg.se.uno.uno
import scala.util.Random

// trait takeCardCreato{
//     def takeCardFromDeck(): String
// }
// object takeCardCreato {
//   def apply(stack: List[(Card)]) = stack.length match{
//     case x if x > 50  => new stackBiggerFive().takeCardFromDeck()
//     case default => new stackSmallerSix().takeCardFromDeck()
//   }
// }

// private class stackBiggerFive extends takeCardCreato{
//     override def takeCardFromDeck(): String = { 
//         uno.controller.players = uno.controller.players.updated(uno.controller.game.currentPlayer,
//                                   Player((uno.controller.players(uno.controller.game.currentPlayer).hand ++ CardDeck.takeCard(1)),
//                                             uno.controller.players(uno.controller.game.currentPlayer).name))
//         return uno.controller.players(uno.controller.game.currentPlayer).toString
//     }
// }


// private class stackSmallerSix extends takeCardCreato{
//     override def takeCardFromDeck(): String = { 
//         val (newStack,oldStack) = CardDeck.stack.splitAt(1)
//         CardDeck.deck = CardDeck.deck ++ oldStack
//         CardDeck.shuffle(scala.util.Random)
//         CardDeck.stack = newStack
//         return stackBiggerFive().takeCardFromDeck()
//     }
// }