package de.htwg.se.uno

object uno{
  val colour = List("🟥","🟩","🟦","🟨","⬛")
  
  val symbol = List( " 0"," 1"," 2"," 3"," 4"," 5"," 6"," 7"," 8"," 9","🚫","🔃","+2","🌈","+4")
  
  val playercount = 6;
  
  val r = scala.util.Random
  val random:( Int => Int) = k => r.nextInt(k)
  
  @main def main():Unit = println(deal(playercount,random))
  
  def hands(nextInt:(Int => Int)):List[(Int,Int)]=(0 until 6).map(k =>(nextInt(5),nextInt(15))).toList
  
  def deal(player: Int,nextInt:(Int => Int)):String = (1 until (player+1)).map(k => playershand(k,nextInt)).mkString + lastcard(nextInt)
    
  def playershand(player: Int,nextInt:(Int => Int)): String = 
    "Hand Player "  + player +"\n" + hands(nextInt).map(k=> colour.apply(k._1) +" "+symbol.apply(k._2)).mkString("  |  ")  + "\n"
  
  def lastcard(nextInt:(Int => Int)): String = "Last Card(stack) \n| " + colour.apply(nextInt(5))+" "+symbol.apply(nextInt(15)) +" |"
}