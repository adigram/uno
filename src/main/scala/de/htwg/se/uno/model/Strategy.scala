package de.htwg.se.uno.model
object CardsAction{
   
def getCards(currentPlayer: Int) = Controller.players(currentPlayer).hand.append(CardDeck.takeCard)
def dropCards(currentPlayer: Int, card: Int) = {
    if(Card.equal(Controller.players(currentPlayer).hand(card), CardDeck.))
}
}