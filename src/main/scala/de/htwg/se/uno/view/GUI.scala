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
import javax.swing.WindowConstants

class GUI(ctrl: ControllerInterface) extends MainFrame with Observer {
 ctrl.add(this)
 title = "UNO"
 resizable = false
 contents = createGame(ctrl).ret
 var dropNOt = CardElements(ctrl).GridPanelDroporNot
 var colour  = CardElements(ctrl).GridPanelColour

 menuBar = new MenuBar{
       contents += new Menu("File"){
          contents+= new MenuItem(Action("Save"){ctrl.save})
          contents+= new MenuItem(Action("Load"){ctrl.load})
       }
       contents += new Menu("Edit"){
          contents+= new MenuItem(Action("Undo"){ctrl.undo()})
          contents+= new MenuItem(Action("Redo"){ctrl.redo()})
       }
 }

 val dialog = new Dialog{
       peer.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)
 }

 override def update: Unit = {
       dialog.visible = false       
       if (CardElements(ctrl).possibleDrop){
              dialog.contents = dropNOt
              dialog.centerOnScreen()
              dialog.modal = true
              dialog.visible = true
       }
       else if (CardElements(ctrl).ColourChoose){
              dialog.contents = colour
              dialog.centerOnScreen()
              dialog.modal = true
              dialog.visible = true       
       }
       else{
              box.contents.clear()
              box.contents += CardElements(ctrl).CreateField
              box.contents += CardElements(ctrl).createBoxPlayer(ctrl.State.players(ctrl.State.currentPlayer))
              box.contents += CardElements(ctrl).GridPanelUNO
       }
       contents = box
}

 val box = new BoxPanel(Orientation.Vertical){
        //border = BorderFactory.createEmptyBorder(10, 0, 10, 0)
        //preferredSize = new Dimension(500, 500)
        resizable = false
        background = new Color(0.2f,0.2f,0.2f)
 }
  pack()
  centerOnScreen()
  open()    
}