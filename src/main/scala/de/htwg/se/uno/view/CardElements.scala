package de.htwg.se.uno.view
import de.htwg.se.uno.model._
import de.htwg.se.uno.controller.Controller
import scala.swing._
import java.awt.Color
import javax.swing.BorderFactory
import scala.swing.event.MouseClicked
import javax.swing.{ImageIcon}
import java.io.File
import java.awt.image.BufferedImage
import scala.swing.event.SelectionChanged.apply

case class CardElements(ctrl: Controller){
var UNOFLAG = false
def button : Button = new Button() {
    reactions += { case event.ButtonClicked(_) =>
      ctrl.doStep(takeCardFromDeckEvent())
    }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 15)
    minimumSize = new Dimension(80, 100)
    maximumSize = new Dimension(80, 100)
    preferredSize = new Dimension(80, 100)
    icon =  ImageIcon("src/ressourcen/cards/hinten.jpg")
  }

def stack : Button = new Button(ctrl.State.stack(0).value.toString) {
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 15)
    minimumSize = new Dimension(80, 100)
    maximumSize = new Dimension(80, 100)
    preferredSize = new Dimension(80, 100)
    background = ctrl.State.stack(0).colour match{
            case Colour.Red => Color.RED
            case Colour.Yellow => Color.YELLOW
            case Colour.Green => Color.GREEN
            case Colour.Blue => Color.BLUE
            case Colour.Black => Color.BLACK
          }

    
  }
  def GridPanelStack : GridPanel =
     new GridPanel(2, 1) {
        contents += button
        contents += stack
        minimumSize = new Dimension(80, 200)
        maximumSize = new Dimension(80, 200)
        preferredSize = new Dimension(80, 200)
    }

  
def buttonDropNot : Button = new Button("Nextplayer"){
  reactions +={
      case event.ButtonClicked(_)  =>
        ctrl.doStep(nextPlayerEvent())
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 10)
    minimumSize = new Dimension(200, 40)
    maximumSize = new Dimension(200, 40)
    preferredSize = new Dimension(200, 40)
  }

def buttonDrop : Button  = new Button("Drop the crad you got from stack"){
  reactions +={
      case event.ButtonClicked(_)  =>
        ctrl.doStep(dropLastCardEvent(None, true))
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 10)
    minimumSize = new Dimension(200, 40)
    maximumSize = new Dimension(200, 40)
    preferredSize = new Dimension(200, 40)
  }

  

  def createBoxPlayer: GridPanel =
    val player = ctrl.State.players(ctrl.State.currentPlayer).hand
    new GridPanel(2, player.size) {
      contents += new TextArea(ctrl.State.players(ctrl.State.currentPlayer).name +":")
      if (player.length != 0) then
        border = BorderFactory.createLineBorder(Color.BLACK, 3)
      for (i <- 0 to player.size - 1) {
        contents += new Button(player(i).value.toString) {
          background = player(i).colour match{
            case Colour.Red => Color.RED
            case Colour.Yellow => Color.YELLOW
            case Colour.Green => Color.GREEN
            case Colour.Blue => Color.BLUE
            case Colour.Black => Color.BLACK
          }
          listenTo(mouse.clicks)
          reactions += { case e: MouseClicked =>
            ctrl.doStep(dropCardEvent(Option(i), UNOFLAG ))
        }
      }
    }
}
 def buttonRed : Button= new Button("Red"){
  reactions +={
      case event.ButtonClicked(_)  =>
        ctrl.doStep(chooseColourEvent(Option(0))) 
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 10)
    minimumSize = new Dimension(100, 40)
    maximumSize = new Dimension(100, 40)
    preferredSize = new Dimension(100, 40)
  }

  def buttonGreen : Button = new Button("Green"){
  reactions +={
      case event.ButtonClicked(_)  =>
        ctrl.doStep(chooseColourEvent(Option(1))) 
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 10)
    minimumSize = new Dimension(100, 40)
    maximumSize = new Dimension(100, 40)
    preferredSize = new Dimension(210000, 40)
  }

  def buttonBlue : Button = new Button("Blue"){
  reactions +={
      case event.ButtonClicked(_)  =>
        ctrl.doStep(chooseColourEvent(Option(2))) 
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 10)
    minimumSize = new Dimension(100, 40)
    maximumSize = new Dimension(100, 40)
    preferredSize = new Dimension(100, 40)
  }

  def buttonDropYellow : Button= new Button("Yellow"){
  reactions +={
      case event.ButtonClicked(_)  =>
        ctrl.doStep(chooseColourEvent(Option(3))) 
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 10)
    minimumSize = new Dimension(100, 40)
    maximumSize = new Dimension(100, 40)
    preferredSize = new Dimension(100, 40)
  }

def LabelColour : Button = new Button("Which Colour do you want)"){
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 10)
    minimumSize = new Dimension(100, 40)
    maximumSize = new Dimension(100, 40)
    preferredSize = new Dimension(100, 40)
  }

  def GridPanelColour : GridPanel =
     new GridPanel(5, 1) {
        contents += LabelColour
        contents += buttonRed
        contents += buttonBlue
        contents += buttonGreen
        contents += buttonDropYellow
        minimumSize = new Dimension(100, 120)
        maximumSize = new Dimension(100, 120)
        preferredSize = new Dimension(100, 120)
    }

def UNOUNOBUTTON : Button = new Button("UNO UNO"){
  reactions +={
      case event.ButtonClicked(_)  =>
        ctrl.doStep(UnoUnoEvent()) 
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 10)
    minimumSize = new Dimension(100, 40)
    maximumSize = new Dimension(100, 40)
    preferredSize = new Dimension(100, 40)
  }

def UNOBUTTON : Button= new Button("UNO"){
  reactions +={
      case event.ButtonClicked(_)  =>
        UNOFLAG =true
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 10)
    minimumSize = new Dimension(100, 40)
    maximumSize = new Dimension(100, 40)
    preferredSize = new Dimension(100, 40)
  }



def undo : Button= new Button("UNDO"){
  reactions +={
      case event.ButtonClicked(_)  =>
        ctrl.undo()
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 10)
    minimumSize = new Dimension(100, 40)
    maximumSize = new Dimension(100, 40)
    preferredSize = new Dimension(100, 40)
  }
def redo : Button= new Button("REDO"){
  reactions +={
      case event.ButtonClicked(_)  =>
        ctrl.redo()
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = new Font("Arial", 1, 10)
    minimumSize = new Dimension(100, 40)
    maximumSize = new Dimension(100, 40)
    preferredSize = new Dimension(100, 40)
  }

def GridPanelDroporNot : GridPanel =
     new GridPanel(2, 1) {
        contents += buttonDropNot
        contents += buttonDrop
        
        minimumSize = new Dimension(200, 80)
        maximumSize = new Dimension(200, 80)
        preferredSize = new Dimension(200, 80)
    }

def GridPanelUNO : GridPanel =
     new GridPanel(2, 2) {
        contents += UNOUNOBUTTON
        contents += UNOBUTTON
        contents += undo
        contents += redo
        minimumSize = new Dimension(200, 80)
        maximumSize = new Dimension(200, 80)
        preferredSize = new Dimension(200, 80)
    }


}





