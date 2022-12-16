package de.htwg.se.uno.util

trait Observer {
  def update(c: Change): Unit
}

class Observable {
  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer): Unit = subscribers = subscribers :+ s

  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

  def notifyObservers(c: Change): Unit = subscribers.foreach(o => o.update(c))
}