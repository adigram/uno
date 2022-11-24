package de.htwg.se.uno.model

trait gameBridge {
  def takeCardFromDeck(State:state) : state 
  def nextPlayer(State:state) : state
}


