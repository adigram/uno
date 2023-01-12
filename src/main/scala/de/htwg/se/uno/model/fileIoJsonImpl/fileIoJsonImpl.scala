package de.htwg.se.uno.model.fileIoJsonImpl

import de.htwg.se.uno.model.FileIOInterface
import java.io._
import play.api.libs.json._
import de.htwg.se.uno.model._
import scala.io.Source

class FileIoJson extends FileIOInterface {

    override def load: GameStateInterface = {
        val source: String = Source.fromFile("game.json").getLines.mkString
        val json: JsValue = Json.parse(source)
        val cp = (json \ "game" \ "currentplayer").get.as[String].toInt
        val P1 = (json \ "game" \ "player1").get
        val P1n = (P1 \ "name").get.as[String]
        val P1h = toHand("player1", json, "handlenght", "hand")
        val P2 = (json \ "game" \ "player2").get
        val P2n = (P2 \ "name").get.as[String]
        val P2h = toHand("player2", json, "handlenght", "hand")
        val direction =  (json \ "game" \ "direction").get.as[String].toBoolean
        val deck = toHand("deck", json,"cardslenght", "cards")
        val stack = toHand("stack",json,"cardslenght", "cards")
        val output =  (json \ "game" \ "output").get.as[String]
        val unoFlag =  (json \ "game" \ "unoFlag").get.as[String].toBoolean

        state(cp,List(Player( P1h, P1n), Player(P2h, P2n)), direction, deck,stack,output, unoFlag )
    }

    def toHand(hand : String,json: JsValue, lenght : String, typ: String):List[Card] = {
        var cards = List[Card]()
        for(i <- 0 to ((json \ "game" \ hand \ lenght).get.as[String].toInt - 1)){
            val tmpCard = (json \ "game" \ hand \ typ).get(i).as[String].split(",")
            cards = cards ++ List(Card(Value.values(tmpCard(0).toInt), Colour.values(tmpCard(1).toInt)))
        }
        
        cards
    }


    override def save(Game: GameStateInterface): Unit = 
        val pw = new PrintWriter(new File("game.json"))
        pw.write(Json.prettyPrint(stateToJson(Game)))
        pw.close

    def stateToJson(Game: GameStateInterface) = {
        Json.obj(
            "game" -> Json.obj(
                "currentplayer" -> Game.currentPlayer.toString(),
                "player1" -> Json.obj(
                    "name" -> Game.players(0).name,
                    "hand" -> Game.players(0).hand.map(k => k.value.InttoString + "," + k.colour.InttoString),
                    "handlenght" -> Game.players(0).hand.length.toString
                ),
                "player2" -> Json.obj(
                    "name" -> Game.players(1).name,
                    "hand" -> Game.players(1).hand.map(k => k.value.InttoString + "," + k.colour.InttoString ),
                    "handlenght" -> Game.players(0).hand.length.toString
                ),
                "direction" -> Game.direction.toString,
                "deck" -> Json.obj(
                    "cards" -> Game.deck.map(k => k.value.InttoString + "," + k.colour.InttoString),
                    "cardslenght" -> Game.deck.length.toString
                ),
                "stack" -> Json.obj(
                    "cards" -> Game.stack.map(k => k.value.InttoString + "," + k.colour.InttoString),
                    "cardslenght" -> Game.stack.length.toString
                ),
                "output" -> Game.output,
                "unoFlag" -> Game.unoFlag.toString()
            )
        )
    }
}


           