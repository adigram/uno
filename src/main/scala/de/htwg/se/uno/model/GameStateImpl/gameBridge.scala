package de.htwg.se.uno.model

trait gameBridge {
  def takeCardFromDeck(State:state, anzahl:Int) : state 
  def nextPlayer(State:state) : state
}