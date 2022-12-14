package de.htwg.se.uno.model

trait GameStateInterface {
  val bridge: gameBridge
  def output: String
  def currentPlayer:Int
  def players:List[Player]
  def direction:Boolean
  def deck:List[Card]
  def stack:List[Card]
  def handle(e:Event):state
  def createZeroCards():List[Card]
  def createNormalCards():List[Card]
  def wildCards():List[Card]
  def createDeck():state
  def createGame():state
  def takeCardFromDeck(): state
  def createPlayers(Namen:List[String]):state
  def takeCard(n: Int): (List[Card] ,List[Card]) 
  def nextPlayer(): state
  def UnoUno(): state
  def dropCard(chosenCard:Option[Int],unoFlag:Boolean): state
  def CardCheck(input: Boolean, selection: Int, unoFlag:Boolean) : state
  def dropLastCard(chosenCard:Option[Int],unoFlag:Boolean): state
  def dropNormalCard(CardNumber: Int, unoFlag:Boolean): state
  def chooseColour(colourNumber: Option[Int]): state

}
