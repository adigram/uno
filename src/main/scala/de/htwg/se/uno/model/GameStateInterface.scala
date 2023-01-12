package de.htwg.se.uno.model

trait GameStateInterface {
  val bridge: gameBridge
  val output: String
  val currentPlayer:Int
  val players:List[Player]
  val direction:Boolean
  val deck:List[Card]
  val stack:List[Card]
  val unoFlag: Boolean

  def handle(e:Event):GameStateInterface
  def createZeroCards():List[Card]
  def createNormalCards():List[Card]
  def wildCards():List[Card]
  def createDeck():GameStateInterface
  def createGame():GameStateInterface
  def takeCardFromDeck(anzahl: Int): GameStateInterface
  def createPlayers(Namen:List[String]):GameStateInterface
  def takeCard(n: Int): (List[Card] ,List[Card]) 
  def nextPlayer(): GameStateInterface
  def UnoUno(): GameStateInterface
  def dropCard(chosenCard:Option[Int]): GameStateInterface
  def CardCheck(input: Boolean, selection: Int) : GameStateInterface
  def dropLastCard(chosenCard:Option[Int]): GameStateInterface
  def dropNormalCard(CardNumber: Int): GameStateInterface
  def chooseColour(colourNumber: Option[Int]): GameStateInterface

}
