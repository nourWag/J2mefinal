/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;


//import cartefidelite.midlet.MidletList;
//import cartefidelite.midlet.Midlet;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author user
 */
public class Statibatton extends Canvas implements CommandListener{
     Command CmdExit= new Command("Exit",Command.EXIT, 0);
    int w = getWidth();
    int h = getHeight();   
//    MidletList mid;
    //Display disp;
    Command cmdback = new Command("Back", Command.BACK, 0);
//   public Statibatton(MidletList m)
    {
//        mid=m;
//        addCommand(CmdExit);
//        addCommand(cmdback);
//        setCommandListener(this);
    }

    
    protected void paint(Graphics g) { 
        String a2014 = "2014";
        String a2015 = "2015";
        String a2016 = "2016";
        String a2017 = "2017";
        int n = 60;
        int m=80;
        int k= 140;
        int p =80;
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, w, h);
        g.setColor(25,120,80);
        g.fillRect(w/9, 25, w/9, n);
        g.setColor(25,50,80);
        g.fillRect(3*w/9,25, w/9, m); 
        g.setColor(255,50,80);
        g.fillRect(5*w/9, 25, w/9, k);   
        g.setColor(255,150,80);
        g.fillRect(7*w/9,25, w/9, p);
        g.setColor(0, 0, 0);
        Font f = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_SMALL);
       g.setFont(f);
       g.drawString(a2014 , w/9, 20,Graphics.BOTTOM|Graphics.LEFT);
       g.drawString(a2015 , 3*w/9, 20,Graphics.BOTTOM|Graphics.LEFT);
       g.drawString(a2016 , 5*w/9, 20,Graphics.BOTTOM|Graphics.LEFT);
       g.drawString(a2017 , 7*w/9, 20,Graphics.BOTTOM|Graphics.LEFT);       
    }

    public void commandAction(Command c, Displayable d) {
       if(c==CmdExit)
       {
//           mid.notifyDestroyed();
       } 
        if( c == cmdback ) {
        }       
    }
}
