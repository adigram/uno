package de.htwg.se.uno.model

trait FileIOInterface:
  def load: GameStateInterface
  def save(Game: GameStateInterface): Unit
  

