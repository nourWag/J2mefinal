package Gui;

//package cartefidelite.gui;
//
//
//
//
//import cartefidelite.entities.cartefidelite;
//import javax.microedition.lcdui.Canvas;
//import javax.microedition.lcdui.Command;
//import javax.microedition.lcdui.CommandListener;
//import javax.microedition.lcdui.Display;
//import javax.microedition.lcdui.Displayable;
//import javax.microedition.lcdui.Graphics;
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// *
// * @author Wael
// */
//public class CanvasCircle extends Canvas implements CommandListener {
//   Command cmdback = new Command("retour", Command.SCREEN, 1);
//    int w = getWidth();
//    int h = getHeight();
//    int a;
//    float b;
//   cartefidelite user ;
//    Display dis ;
//
//    public CanvasCircle(float pourcentage_vente ,cartefidelite user , Display dis) {
//     
//        this.dis =dis ;
//        this.user = user ;
//        this.setCommandListener(this);
//        this.addCommand(cmdback);
//        this.b = 360 * pourcentage_vente;
//        this.a = (int) this.b;
//    }
//
//    protected void paint(Graphics g) {
//
//        //Blanc
//        g.setColor(255, 255, 255);
//        g.fillRect(0, 0, w, h);
//
//        //Noir
//        g.setColor(0, 0, 0);
//  
//        g.setColor(0, 255, 0);
//        g.fillArc(w / 2 - 50, h / 2 - 25, 120, 120, 0, 360);
//        g.drawString("location ", w / 2 - 30, 15, 0);
//        
//        g.setColor(0, 0, 250);
//        g.fillArc(w / 2 - 50, h / 2 - 25, 120, 120, 0, a);
//        g.drawString("vente", w / 2 - 30, 2, 0);
//    }
//
//    public void commandAction(Command c, Displayable d) {
//if(c==cmdback) 
//{
//    new Statistique(dis,user);
//}       
//}
//    
//    
//    
//}
