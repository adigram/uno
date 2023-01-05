package  de.htwg.se.uno.model 
enum Value {
  case Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine,
        Skip, Reverse, DrawTwo, Wild, WildFour
  override def toString = {
      this match {
        case Zero => " 0" case One => " 1"
        case Two => " 2" case Three=> " 3" case Four => " 4" case Five => " 5"
        case Six=> " 6" case Seven => " 7" case Eight => " 8"  case Nine=> " 9"
        case Skip => "🚫" case Reverse => "🔃" case DrawTwo => "+2"
        case Wild => "🌈" case WildFour => "+4"
      }
  }
  def InttoString = {
    this match {
     case Zero => "0" case One => "1"
     case Two => "2" case Three=> "3" case Four => "4" case Five => "5"
     case Six=> "6" case Seven => "7" case Eight => "8"  case Nine=> "9"
     case Skip => "10" case Reverse => "11" case DrawTwo => "12"
     case Wild => "13" case WildFour => "14"
    }

  }
  
}