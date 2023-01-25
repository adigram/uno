package de.htwg.se.uno.view
import de.htwg.se.uno.model._
import de.htwg.se.uno.controller.Controller
import scala.swing._
import java.awt.Color
import java.awt.Image;
import javax.swing.BorderFactory
import scala.swing.event.MouseClicked
import javax.swing.{ImageIcon}
import java.io.File
import java.awt.image.BufferedImage
import javax.imageio.ImageIO;
import scala.swing.event.SelectionChanged.apply
import de.htwg.se.uno.controller.ControllerInterface

val width = 70;
val height = 100;
val cardSize = new Dimension(width, height);

val image_back  = ImageIO.read(new File("src/resources/cards/back.png"  )).getScaledInstance(width, height, Image.SCALE_SMOOTH);
val image_black = ImageIO.read(new File("src/resources/cards/black.png" )).getScaledInstance(width, height, Image.SCALE_SMOOTH);
val image_blue  = ImageIO.read(new File("src/resources/cards/blue.png"  )).getScaledInstance(width, height, Image.SCALE_SMOOTH);
val image_green = ImageIO.read(new File("src/resources/cards/green.png" )).getScaledInstance(width, height, Image.SCALE_SMOOTH);
val image_red   = ImageIO.read(new File("src/resources/cards/red.png"   )).getScaledInstance(width, height, Image.SCALE_SMOOTH);
val image_yellow= ImageIO.read(new File("src/resources/cards/yellow.png")).getScaledInstance(width, height, Image.SCALE_SMOOTH);

val font_emoji   = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File("src/resources/NotoEmoji-Bold.ttf"))
val arial_normal = new Font("Arial", 1, 15)
val arial_big    = new Font("Arial", 1, 25)
val emoji_big    = font_emoji.deriveFont(25f)

val color_bg = new Color(0.2f,0.2f,0.2f)

case class CardElements(ctrl: ControllerInterface){


def deck = new Button() {
    reactions += { case event.ButtonClicked(_) =>
      ctrl.doStep(takeCardFromDeckEvent())
    }
    border = BorderFactory.createEmptyBorder()
    //contentAreaFilled = false
    //borderPainted = false
    font = arial_normal
    preferredSize = cardSize
    icon =  ImageIcon(image_back);    
  }

def stack = renderCard(ctrl.State.stack(0))

def renderCard(card:Card) = new Button(card.value.toString) {
    font = card.value match {
      case Value.Skip | Value.Wild | Value.Reverse => emoji_big
      case _ => arial_big
    }
    foreground = Color.WHITE
    horizontalTextPosition = Alignment.Center
    preferredSize = cardSize
    icon = card.colour match{
      case Colour.Red    => ImageIcon(image_red);
      case Colour.Yellow => ImageIcon(image_yellow)
      case Colour.Green  => ImageIcon(image_green)
      case Colour.Blue   => ImageIcon(image_blue)
      case Colour.Black  => ImageIcon(image_black)
    }
}

def CreateField = new FlowPanel {
  contents += deck
  contents += stack
  background = color_bg//new Color(0.0f,0.0f,0.0f,0.0f)
}

  
def buttonDropNot = new Button("Keep Card"){
  reactions +={ case event.ButtonClicked(_)  =>
    ctrl.doStep(nextPlayerEvent())
  }
  border = BorderFactory.createRaisedSoftBevelBorder
  font = arial_normal
  preferredSize = new Dimension(200, 40)
}

def buttonDrop = new Button("Play Card"){
  reactions +={ case event.ButtonClicked(_)  =>
    ctrl.doStep(dropLastCardEvent(None))
  }
  border = BorderFactory.createRaisedSoftBevelBorder
  font = arial_normal
  preferredSize = new Dimension(200, 40)
}

  

def createBoxPlayer(player:Player) =
  new BoxPanel(Orientation.Vertical){
    background = color_bg //new Color(0.0f,0.0f,0.0f,0.0f)
    contents+= new BorderPanel {
      add(new Button(player.name){
        font =  arial_normal
        background = Color.WHITE
        contentAreaFilled = false
      }, BorderPanel.Position.Center)
    } 
    contents+= new FlowPanel {
      background = color_bg //new Color(0.0f,0.0f,0.0f,0.0f)
      if (player.hand.length != 0) then
        border = BorderFactory.createLineBorder(Color.WHITE, 3)
        for (i <- 0 to player.hand.size - 1) {
        val playercard = renderCard(player.hand(i))
        playercard.reactions += { case event.ButtonClicked(_) =>
            if (ctrl.State.unoFlag)
              ctrl.doStep(UnoEvent( Option(i) ))
            else
              ctrl.doStep(dropCardEvent( Option(i) ))
        }
        contents += playercard
    }
  }
}

 def buttonRed : Button= new Button("Red"){
  reactions +={
      case event.ButtonClicked(_)  =>
        ctrl.doStep(chooseColourEvent(Option(0))) 
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = arial_normal
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
    font = arial_normal
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
    font = arial_normal
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
    font = arial_normal
    minimumSize = new Dimension(100, 40)
    maximumSize = new Dimension(100, 40)
    preferredSize = new Dimension(100, 40)
  }

def LabelColour : Button = new Button("Choose your Color"){
    border = BorderFactory.createRaisedSoftBevelBorder
    font = arial_normal
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
        if(ctrl.doStep(UnoUnoEvent()).output.apply(0).equals('T')) System.exit(0)
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = arial_normal
    minimumSize = new Dimension(100, 40)
    maximumSize = new Dimension(100, 40)
    preferredSize = new Dimension(100, 40)
  }

def UNOBUTTON : Button= new Button("UNO"){
  reactions +={
      case event.ButtonClicked(_)  =>
        ctrl.State = state(ctrl.State.currentPlayer, ctrl.State.players,ctrl.State.direction, ctrl.State.deck,
         ctrl.State.stack, ctrl.State.output,true)
        }
    border = BorderFactory.createRaisedSoftBevelBorder
    font = arial_normal
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
        minimumSize = new Dimension(200, 80)
        maximumSize = new Dimension(200, 80)
        preferredSize = new Dimension(200, 80)
    }
  def possibleDrop : Boolean = ctrl.State.output.apply(0).equals('D')
  def ColourChoose : Boolean = ctrl.State.output.apply(0).equals('W')
}