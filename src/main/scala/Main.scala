@main def main():Unit = println(hand())

val Farben: List[String] = List[String]("🟥","🟩","🟦","🟨","⬛")

val Symbole: List[String] = List[String]( "0","1","2","3","4","5","6","7","8","9","🚫","🔃","+2","🌈","+4")

val HandP1: List[(Int,Int)] = List[(Int,Int)]( (1,8),(3,4),(3,10),(4,13),(4,14),(2,3) )
val lastCard: (Int,Int) = (1,3)
def hand():String={
  "Hand Spieler 1 \n| "+HandP1.map(k=> Farben.apply(k._1)+" "+Symbole.apply(k._2)).mkString(" | ") +" |"
}
 
def haufen():String={
  "Letzte Karte \n| " + Farben.apply(lastCard._1)+" "+Symbole.apply(lastCard._2)
}