package de.htwg.se.uno.model
import de.htwg.se.uno.uno
trait Event()
      
class selectionEvent() extends Event() {
  def selection: Unit = {
    println("Possible instructions:")
    printf("\tl = leave\n")
    printf("\ts = Take a new Card from Stack\n")
    printf("\tr = Put a Card from Hand into GameBoard\n")
    printf("\tu = Call UNO\n")
    printf("\tw = Call UNO UNO\n")
       
  }
}

case class callUno() extends Event
case class callUnoUno() extends Event
case class nextPlayerEvent() extends Event

case class startEvent() extends Event {
  def start: Unit = {
    println("Lets Start the Game!")
    
  }
}

case class leaveGameEvent() extends Event {
  def leaveGame: Unit = {
    println("Player finished Game - Game is over! ")
    
  }

}

case class wrongInstructionEvent() extends Event {
  def wrongInstruction: Unit = {
    println("Your Input didn't macht to the Instructions.")
    uno.tui.input(uno.controller.handle(selectionEvent()))  
  }
  
}

case class callUnoEvent() extends Event {
  def callUno: Unit = {
    println("UNO") 
  }
}

case class callUnoUnoEvent() extends Event {
  def callUno: Unit = {
    println("UNO UNO") 
  }
}

case class takeCardFromStackEvent() extends Event {
  def takeCardFromStack: String = {
     val temp = "Your new Deck:\n" + uno.controller.players(uno.controller.game.currentPlayer).toString
     println("Your new Deck:\n" + uno.controller.players(uno.controller.game.currentPlayer).toString)
     return temp
  }
}

case class roundFinishedEvent() extends Event {
  def roundFinished: Unit = {
    uno.controller.game.roundFinished()
  }
}

case class selectCardEvent() extends Event {
  def selectCard: String = {
    val temp = "Please select the Card you want to Push"
    println(temp)
    return temp
  }
}


case class selectedWrongCardEvent() extends Event {
  def selectedWrongCard: String = {
    val temp = "Your Card does mot march with the card on the stack"
    println(temp)
    uno.tui.input(uno.controller.handle(selectionEvent()))
    return temp
  }
}







