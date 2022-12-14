package de.htwg.se.uno.model
import de.htwg.se.uno.util._

trait GameStateInterface {
  val bridge: gameBridge
  def output: String
  def currentPlayer:Int
  def players:List[Player]
  def direction:Boolean
  def trigger: Trigger
  def deck:List[Card]
  def stack:List[Card]
  def handle(e:Event):state
  def takeCardFromDeck(): state
  def nextPlayer(): state
  def UnoUno(): state
  def dropCard(chosenCard:Option[Int],unoFlag:Boolean): state
  def CardCheck(input: Boolean, selection: Int, unoFlag:Boolean) : state
  def dropLastCard(chosenCard:Option[Int],unoFlag:Boolean): state
  def dropNormalCard(CardNumber: Int, unoFlag:Boolean): state
  def chooseColour(colourNumber: Option[Int]): state

}
