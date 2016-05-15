/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TunisiaMall;

import Gui.Acceuil;
import Gui.Acceuilsn;
import Gui.Login1Form;

import com.sun.lwuit.Button;
import com.sun.lwuit.Label;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import java.io.IOException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;



public class Midlet2 extends MIDlet implements CommandListener, Runnable {
    Command cmdExit=new Command("exit",Command.EXIT,0);
    
    public Display disp = Display.getDisplay(this);

    public static Midlet2 INSTANCE=null;
    
    
    Image splash;
    Alert sp = new Alert("Bienvenue");
     public Midlet2() {
    
     try {
            INSTANCE =this;
             splash = Image.createImage("/mall.jpg");

        } catch (IOException e) {
            e.printStackTrace();
        }}

    public void startApp() {
         sp.setImage(splash);
        sp.setTimeout(30000);
         disp.flashBacklight(2000);
       ///INSTANCE=this;
      // disp.setCurrent(new Login1Form());
         disp.setCurrent(new SplashScreen(this));
        
//        try{
//        mImage = Image.createImage("/Travel.png");
//
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//         a0.setImage(mImage);
//         
//         
//         disp.setCurrent(a0,f);
//        a0.setTimeout(500000000);
//        try {
//            Thread.sleep(3000);//
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//        INSTANCE = this;
//           
//     
//         try{
//        mImage = Image.createImage("images/altitude.jpg");
//
//        }
//        catch(IOException e){
//            e.printStackTrace();
//            System.out.println("image");
//        }
//         al.setImage(mImage);
//         al.setTimeout(2000);
//        try { 
//            mImage = Image.createImage("images/colis2.png");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        l1.append("Packages List", mImage);
//        try { 
//            mImage = Image.createImage("images/air.png");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }        
//        l1.append("Airlines List", mImage);
//        try { 
//            mImage = Image.createImage("images/stat.png");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        l1.append("Statistiques",mImage);
//        try { 
//            mImage = Image.createImage("images/pos.png");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }        
//        l1.append("GPS", mImage);
//            l1.addCommand(cmdExit);
//        l1.setCommandListener(this);
//        disp.setCurrent(al,l1);   
        
        
        
         
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
//        if(l1.getSelectedIndex()==0) {
//            PersonneList listepack = new PersonneList("Packages", List.IMPLICIT, disp);    
//            listepack.addCommand(cmdExit);
//            listepack.setCommandListener(this); 
//            disp.setCurrent(listepack);
//         }
    }

    public void run() {
        
    }
}
