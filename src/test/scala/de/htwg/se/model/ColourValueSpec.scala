package de.htwg.se.model
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.uno.model.*

class ColourValueSpec extends AnyWordSpec with Matchers {
    "A Value" when {
        "when its printed with its value" should{
            "look like" in{
                Value.Zero.toString should be (" 0")
                Value.One.toString should be (" 1")
                Value.Two.toString should be (" 2")
                Value.Three.toString should be (" 3")
                Value.Four.toString should be (" 4")
                Value.Five.toString should be (" 5")
                Value.Six.toString should be (" 6")
                Value.Seven.toString should be (" 7")
                Value.Eight.toString should be (" 8")
                Value.Nine.toString should be (" 9")
                Value.Skip.toString should be ("🚫")
                Value.Reverse.toString should be ("🔃")
                Value.DrawTwo.toString should be ("+2")
                Value.Wild.toString should be ("🌈")
                Value.WildFour.toString should be ("+4")  
            }  
        }
    }
    "A Value" when { 
        "when its printed as a number" should{
            "look like" in{
                Value.Zero.InttoString     should be ("0")
                Value.One.InttoString      should be ("1")
                Value.Two.InttoString      should be ("2")
                Value.Three.InttoString    should be ("3")
                Value.Four.InttoString     should be ("4")
                Value.Five.InttoString     should be ("5")
                Value.Six.InttoString      should be ("6")
                Value.Seven.InttoString    should be ("7")
                Value.Eight.InttoString    should be ("8")
                Value.Nine.InttoString     should be ("9")
                Value.Skip.InttoString     should be ("10")
                Value.Reverse.InttoString  should be ("11")
                Value.DrawTwo.InttoString  should be ("12")
                Value.Wild.InttoString     should be ("13")
                Value.WildFour.InttoString should be ("14")  
            }  
        }
    }
    "A colour" when{
        "when its printed" should{
            "look like" in{
                Colour.Red.toString should be ("🟥")
                Colour.Green.toString should be ("🟩")
                Colour.Blue.toString should be ("🟦")
                Colour.Yellow.toString should be ("🟨")
                Colour.Black.toString should be ("⬛")
            }
        }
    }
    "A colour" when{
        "when its printed as a number" should{
            "look like" in{
                Colour.Red.InttoString should be ("0")
                Colour.Green.InttoString should be ("1")
                Colour.Blue.InttoString should be ("2")
                Colour.Yellow.InttoString should be ("3")
                Colour.Black.InttoString should be ("4")
            }
        }
    }

}