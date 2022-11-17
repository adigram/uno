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
    
   
 }
