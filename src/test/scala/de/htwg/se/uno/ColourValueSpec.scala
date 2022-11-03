package de.htwg.se.uno
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model._

class ColourValueSpec extends AnyWordSpec with Matchers {
     "A Value" when {
        val value = List(Value.Zero, Value.One, Value.Two, Value.Three, Value.Four,
                        Value.Five, Value.Six, Value.Seven, Value.Eight, Value.Nine,
                        Value.Skip, Value.Reverse, Value.DrawTwo, Value.Wild, Value.WildFour) 
        "when its printed" should{
            "look like" in{
                value(0).toString should be (" 0")
                value(1).toString should be (" 1")
                value(2).toString should be (" 2")
                value(3).toString should be (" 3")
                value(4).toString should be (" 4")
                value(5).toString should be (" 5")
                value(6).toString should be (" 6")
                value(7).toString should be (" 7")
                value(8).toString should be (" 8")
                value(9).toString should be (" 9")
                value(10).toString should be ("🚫")
                value(11).toString should be ("🔃")
                value(12).toString should be ("+2")
                value(13).toString should be ("🌈")
                value(14).toString should be ("+4")
                
            }
            
            
        }
    }
    "A colour" when{
        val colour = List(Colour.Blue, Colour.Yellow, Colour.Black)
        "when its printed" should{
            "look like" in{
                colour(0).toString should be ("🟦")
                colour(1).toString should be ("🟨")
                colour(2).toString should be ("⬛")
            }
        }
    }


}
