package de.htwg.se.uno.util

trait Observer:
  def update(e: Trigger): Unit


trait Observable:
  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer): Unit = subscribers = subscribers :+ s

  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

  def notifyObservers(t:Trigger): Unit = subscribers.foreach(o => o.update(t))
 
enum Trigger:
  case print
  case dropAftertake
  case colourChoose
