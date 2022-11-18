package de.htwg.se.uno.model
import de.htwg.se.uno.uno
import de.htwg.se.uno.util._
import de.htwg.se.uno.model

 case class Game(){
    var currentPlayer: Int = 0
    var GameDirection: Boolean = true
    
    def setCurrentPlayer()={
        GameDirection match {
            case true => currentPlayer = currentPlayer % uno.controller.players.length
            case false => currentPlayer = (currentPlayer-2) % uno.controller.players.length
        }
    }
    def roundFinished()={  
        currentPlayer += 1
        setCurrentPlayer()
        println(currentPlayer.toString())
        println("It's your turn "+ uno.controller.players(currentPlayer).name +":" ) 
        uno.tui.input(state.handle(selectionEvent())) 
    }
    def handleRequest(input: String) : Event =  {      //toDo richtige struktur für chain of res pattern ?
         toInt(input) match {
            case x if x >= 0 && x < uno.controller.players(uno.controller.game.currentPlayer).hand.length =>
                                    handleRequest2(uno.controller.players(uno.controller.game.currentPlayer).hand(x).
                                    equal(uno.controller.players(uno.controller.game.currentPlayer).hand(x), CardDeck.stack(0)))
                                    
            case default =>  return wrongInstructionEvent()
        }
    }
    
    def handleRequest2(input: Boolean) : Event =  {
         input match {
            case true => return leaveGameEvent()    // Aufruf der Karten Funktin --> Runde beenden 
            case false =>  return wrongInstructionEvent()
        }
    }



    def toInt(s: String): Int = {
        try {
             s.toInt
        } catch {
            case e: Exception => -1
        }
        }
 }
