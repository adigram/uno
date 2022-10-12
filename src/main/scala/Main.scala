@main def main():Unit = println(austeilen(6))

val colour: List[String] = List[String]("🟥","🟩","🟦","🟨","⬛")

val symbol: List[String] = List[String]( "0","1","2","3","4","5","6","7","8","9","🚫","🔃","+2","🌈","+4")

val r = scala.util.Random

def haende2():List[(Int,Int)]=(0 until 6).map(k =>(r.nextInt(5),r.nextInt(15))).toList

def austeilen(spieler: Int):String={
  val r = scala.util.Random
  (1 until (spieler+1)).map(x => "Hand Spieler "  + x +"\n"+
   haende2().map(k=> colour.apply(k._1) +" "+symbol.apply(k._2)).mkString("  |  ")  + "\n").mkString +
   "Letzte Karte(Stappel) \n| " +   colour.apply(r.nextInt(3))+" "+symbol.apply(r.nextInt(8)) +" |"
}
 
