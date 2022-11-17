package de.htwg.se.uno.model

object state {
      var state = startEvent().start 
    
    def handle(e: Event ) = {
        e match {
            case a: startEvent => state = startEvent().start
            case b: selectionEvent => state = selectionEvent().selection
            case c: wrongInstructionEvent =>state = wrongInstructionEvent().wrongInstruction
            case d: callUnoEvent => state = callUnoEvent().callUno
            case e: takeCardFromStackEvent => state = takeCardFromStackEvent().takeCardFromStack
            case f: roundFinishedEvent => state = roundFinishedEvent().roundFinished
            case g: leaveGameEvent => state =  leaveGameEvent().leaveGame
        }
        state 
    }
   
}
  

