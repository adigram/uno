@main def main():Unit = println(deal(6))

val Colors: List[String] = List[String]("🟥","🟩","🟦","🟨","⬛")

val Symbols: List[String] = List[String]( "0","1","2","3","4","5","6","7","8","9","🚫","🔃","+2","🌈","+4")

def hands(): List[(Int,Int)]={
   val r = scala.util.Random
   val result = List[(Int,Int)]( (r.nextInt(5),r.nextInt(15)),(r.nextInt(5),r.nextInt(15)),(r.nextInt(5),r.nextInt(15)),
   (r.nextInt(5),r.nextInt(15)),(r.nextInt(5),r.nextInt(15)),(r.nextInt(5),r.nextInt(15)) )
   result
}

def deal(player: Int):String={
  val r = scala.util.Random
  (1 until (player+1)).map(x => "Hand Player "  + x +"\n"+
   hands().map(k=> Colors.apply(k._1) +" "+Symbols.apply(k._2)).mkString("  |  ")  + "\n").mkString +
   "Last card(stack) \n| " +   Colors.apply(r.nextInt(3))+" "+Symbols.apply(r.nextInt(8)) +" |"
}
 
