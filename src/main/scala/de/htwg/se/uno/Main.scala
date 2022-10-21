package Uno

object uno{
  val colour = List("🟥","🟩","🟦","🟨","⬛")
  
  val symbol = List( " 0"," 1"," 2"," 3"," 4"," 5"," 6"," 7"," 8"," 9","🚫","🔃","+2","🌈","+4")
  
  val playercount = 6;
  
  val r = scala.util.Random
  
  @main def main():Unit = println(deal(playercount))
  
  def hands():List[(Int,Int)]=(0 until 6).map(k =>(r.nextInt(5),r.nextInt(15))).toList
  
  def deal(player: Int):String = (1 until (player+1)).map(k => playershand(k)).mkString + lastcard()
    
  def playershand(player: Int): String = 
    "Hand Player "  + player +"\n" + hands().map(k=> colour.apply(k._1) +" "+symbol.apply(k._2)).mkString("  |  ")  + "\n"
  
  def lastcard(): String = "Last Card(stack) \n| " + colour.apply(r.nextInt(5))+" "+symbol.apply(r.nextInt(15)) +" |"
}