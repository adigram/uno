package de.htwg.se.uno.model.fileIoXmlImpl

import de.htwg.se.uno.model._
import scala.xml.{NodeSeq, PrettyPrinter}
import java.io.*


class FileIOXml extends FileIOInterface{

    override def load: GameStateInterface = {
        val file = scala.xml.XML.loadFile("game.xml")
        val CP = (file \\ "game" \\ "currentPlayer").text
        val player1 = (file \\ "game" \ "player1")
        val p1N = (player1 \\ "name").text
        val p1H = (player1 \\ "hand").text
        val H1 = toHand(p1H)
        val player2 = (file \\ "game" \ "player2")
        val p2N = (player2 \\ "name").text
        val p2H = (player2 \\ "hand").text
        val H2 = toHand(p2H)
        val dir = ( file \\ "game" \\ "direction").text
        val deck = toCard(( file \\ "game" \\ "deck" \\ "cards").text)
        val stack = toCard(( file \\ "game" \\ "stack" \\ "cards").text)
        val output = ( file \\ "game" \\ "output").text
        state(CP.toInt,List(Player( H1, p1N), Player(H2, p2N)), dir.toBoolean, deck,stack,output, false )
    }

    def toHand(hand: String):List[Card] =  {
        val tmp = hand.split(" ")
        var cards = List[Card]()
        for(i <- tmp){
            val tmpCard = i.split(",")
            cards = cards ++ List(Card(Value.values(tmpCard(0).toInt), Colour.values(tmpCard(1).toInt)))
        }
        cards 
    }

    def toCard(hand: String):List[Card] =  {
        val tmp = hand.split("_", hand.length())
        var iterator = 0
        var cards = List[Card]()
        for(i <- 0 to (tmp.length - 2)){
            val tmp2 = tmp(i).split("-")
            val tmpCard = tmp2(1).split(",")
            cards = cards ++ List(Card(Value.values(tmpCard(0).toInt), Colour.values(tmpCard(1).toInt)))
        }
        printf(cards.toString())
        cards
    }

    override def save(Game: GameStateInterface): Unit = {
        val pw = new PrintWriter(new File("game.xml"))
        val prettyPrinter = new PrettyPrinter(120, 4)
        val xml = prettyPrinter.format(gametoXml(Game))
        pw.write(xml)
        pw.close
    }

    def gametoXml(Game: GameStateInterface) =
      <game>
            <currentPlayer>{Game.currentPlayer.toString}</currentPlayer>
            <player1>{playertoXml(Game.players(0))}</player1>
            <player2>{playertoXml(Game.players(1))}</player2>
            <direction>{Game.direction.toString}</direction>
            <deck>{cardtoXml(Game.deck)}</deck>
            <stack>{cardtoXml(Game.stack)}</stack>
            <output>{Game.output}</output>
            <unoFlag>{Game.unoFlag.toString}</unoFlag>
    </game>
  
    def playertoXml(player:Player) = 
        <player>
            <name>{player.name}</name>
            <hand>{player.hand.map(k => k.value.InttoString + "," + k.colour.InttoString + " ")}</hand>
        </player>

    def cardtoXml(cards: List[Card]) =
        <cards>{cards.map(k => "*-"+ k.value.InttoString + "," + k.colour.InttoString + "_")}</cards>

}