package de.htwg.se.uno.view
import scala.swing.ListView._
import scala.swing.Swing._
import scala.swing._
import scala.swing.event._
import de.htwg.se.uno.controller._
import de.htwg.se.uno.util._
import de.htwg.se.uno.model._
import java.awt.Color
import javax.swing.BorderFactory

class GUI1(ctrl: Controller) extends MainFrame with Observer {
 ctrl.add(this)
 title = "UNO"
 resizable = false
 contents = createGame(ctrl).ret
 var dropNOt = CardElements(ctrl).GridPanelDroporNot
 var colour = CardElements(ctrl).GridPanelColour

 override def update: Unit = {
       
       dropNOt.visible = false
       colour.visible = false
       
       box.contents.clear()
       
       if (CardElements(ctrl).possibleDrop){
              dropNOt.visible = true
              box.contents += dropNOt
              
       }
       else if (CardElements(ctrl).ColourChoose){
              colour.visible = true
              box.contents += colour
              
       }
       else{
              box.contents += CardElements(ctrl).GridPanelStack
              box.contents += CardElements(ctrl).createBoxPlayer
              box.contents += CardElements(ctrl).GridPanelUNO
              box.contents += dropNOt
              box.contents += colour
              
       }
        contents = box
}

 val box = new BoxPanel(Orientation.Vertical){
        border = BorderFactory.createEmptyBorder(10, 0, 10, 0)
        minimumSize = new Dimension(550, 500)
        maximumSize = new Dimension(550, 500)
        preferredSize = new Dimension(550, 500)
        resizable = false
 
 }
 


  

  
  pack()
  centerOnScreen()
  open()

    
}

