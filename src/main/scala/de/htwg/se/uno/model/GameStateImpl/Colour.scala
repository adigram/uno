package  de.htwg.se.uno.model 
 
enum Colour {
  case Red, Green, Blue, Yellow, Black
  override def toString = {
    this match {
      case Red => "🟥"
      case Green => "🟩"
      case Blue => "🟦"
      case Yellow => "🟨"
      case Black => "⬛"
    }
  }

  def InttoString = {
    this match {
      case Red => "0"
      case Green => "1"
      case Blue => "2"
      case Yellow => "3"
      case Black => "4"
    }

  }
}
