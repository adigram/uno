@main def main():Unit = println(hand())

val invList: List[(Int,String)] = List[(Int,String)]( (3,"🟥"),(3,"🟩"),(1,"🟦"),(2,"🟨"))

val Symbol: List[(Int,String)] = List[(Int,String)]( (0,"0"),(1,"1"),(2,"2"),(3,"3"),(4,"4"),(4,"5"),(4,"6"),(4,"7"))

def hand():String={
  "Spieler 1 deine Hand \n| "+invList.map(k=>k._1.toString()+""+k._2).mkString(" | ") +" |"
}