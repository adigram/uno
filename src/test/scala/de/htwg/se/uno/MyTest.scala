// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package de.htwg.se.uno

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

val e   = Iterator(0)
val r5  = Iterator(3,2,4,2,3,0,1,5,2,3,0,2,1,4,1,3,0,4,5)
val r15 = Iterator(8,15,14,11,7,3,8,5,9,3,12,2,1,4,6,1,3,10,4,9,5)

val randomList: List[Iterator[Int]] = List(e,e,e,e,e,r5,e,e,e,e,e,e,e,e,e,r15)
val random:( Int => Int) = k => randomList.apply(k).next()

class MainSpec extends AnyWordSpec with Matchers {

  "Main" should {
    val output = uno.lastcard(random)
    "lastcard() print"  in {
      output should be("Last Card(stack) \n| 🟨  8 |")
    }
    val handsList = uno.hands(random)
    "hands() List"  in {
      handsList should be(List((2,15), (4,14), (2,11), (3,7), (0,3), (1,8)))
    }
  }


}