@main def main():Unit = println(austeilen(6))

val Farben: List[String] = List[String]("🟥","🟩","🟦","🟨","⬛")

val Symbole: List[String] = List[String]( "0","1","2","3","4","5","6","7","8","9","🚫","🔃","+2","🌈","+4")

def haende(): List[(Int,Int)]={
   val r = scala.util.Random
   val ergebnis = List[(Int,Int)]( (r.nextInt(5),r.nextInt(15)),(r.nextInt(5),r.nextInt(15)),(r.nextInt(5),r.nextInt(15)),
   (r.nextInt(5),r.nextInt(15)),(r.nextInt(5),r.nextInt(15)),(r.nextInt(5),r.nextInt(15)) )
   ergebnis
}

def austeilen(spieler: Int):String={
  val r = scala.util.Random
  (1 until (spieler+1)).map(x => "Hand Spieler "  + x +"\n"+
   haende().map(k=> Farben.apply(k._1) +" "+Symbole.apply(k._2)).mkString("  |  ")  + "\n").mkString +
   "Letzte Karte(Stappel) \n| " +   Farben.apply(r.nextInt(3))+" "+Symbole.apply(r.nextInt(8)) +" |"
}
 
