/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import TunisiaMall.Midlet2;
import com.sun.lwuit.Button;
import com.sun.lwuit.Component;
import com.sun.lwuit.Display;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author nour
 */
public class Acceuil implements CommandListener,ActionListener{
    StringItem st=new StringItem("message : ", null);
    TextField txt =new TextField("jour", null, 50, TextField.ANY);
    String[] choix=new String[]{"imsek","iftar"};
    ChoiceGroup chG=new ChoiceGroup("compétence", ChoiceGroup.POPUP, choix, null); 
   
   
    Image img;
    HttpConnection http;
    DataInputStream data;
   
    
    String url="http://localhost:8000/3A14.txt";
    Command cmd =new Command("connexion", Command.OK, 0);
    
    javax.microedition.lcdui.Display disp ;
    Displayable displ;
    public Acceuil(javax.microedition.lcdui.Display disp) {
        
       this.disp = disp;
         //init the LWUIT display
        Display.init(this);
        
        //setting the application theme
        try{
            Resources r=Resources.open("/LWUITtheme.res");
            UIManager.getInstance()
                    .setThemeProps(r.getTheme
                    (r.getThemeResourceNames()[0]));
        }catch (Exception e){}
        
        com.sun.lwuit.Form mainForm = new com.sun.lwuit.Form("Acceuil");
        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        final Button button1=new Button("Tunisia Mall");
        button1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                button1.setText("Bienvenue");
            }
        });
        
        mainForm.addComponent(button1);
        try {
            com.sun.lwuit.Image icon1 = com.sun.lwuit.Image.createImage("/icons/8.png");
            Button button2=new Button(icon1);
            button2.setText("Lister Produits");
            button2.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
             new AffichageProduit(Midlet2.INSTANCE.disp);
             }

});
            mainForm.addComponent(button2);
com.sun.lwuit.Image icon2 = com.sun.lwuit.Image.createImage("/icons/19.png");
            Button button3=new Button(icon2);
            button3.setText("ajouter Produit");
            
            button3.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
             new AjoutProduit(Midlet2.INSTANCE.disp);
             }

});
            mainForm.addComponent(button3);
            com.sun.lwuit.Image icon3 = com.sun.lwuit.Image.createImage("/icons/9.png");
            Button button4=new Button(icon3);
            button4.setText("Localisation de Tunisia Mall");
            button4.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
               Midlet2.INSTANCE.disp.setCurrent(new GoogleMapsZoomCanvas(Midlet2.INSTANCE,displ));  
             //Midlet.mymid.disp.setCurrent(new GoogleMapsZoomCanvas(Midlet.mymid,displ));
             }

});
            button4.setTextPosition(Component.RIGHT);
            mainForm.addComponent(button4);
            
            com.sun.lwuit.Image icon4 = com.sun.lwuit.Image.createImage("/icons/12.png");
            Button button5=new Button(icon4);
            button5.setText("video Tunisia Mall");
            button5.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
              new video(Midlet2.INSTANCE.disp);
             }

});
            button5.setTextPosition(Component.RIGHT);
            mainForm.addComponent(button5);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        

        mainForm.setTransitionOutAnimator(CommonTransitions.createFade(400));
       com.sun.lwuit.Command cmdBack= new com.sun.lwuit.Command("Back", 2);
    
        mainForm.addCommand(cmdBack);
        mainForm.setCommandListener(this);
        //mainForm.addCommand(new com.sun.lwuit.Command("Right Soft key", 2));
        mainForm.show();
        
    
    }
    public void connexion() {
        // kona 7Atin connexion 3al base lina mba3ed k sta3malna thread badalna f run
       
    }

    public void commandAction(Command c, Displayable d) {
        if(c==cmd){
        //Thread th =new Thread(this);
        //th.start();
        }
        
    }

   /* public void run() {
         StringBuffer stb=new StringBuffer();
        try {
            http=(HttpConnection) Connector.open(url);
            data=http.openDataInputStream();
            int ch;
            while ((ch=data.read())!=-1) { 
                stb.append(""+(char)ch);
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        st.setText(stb.toString());
    }*/

    public void actionPerformed(ActionEvent ae) {
 new Acceuilsn(Midlet2.INSTANCE.disp);
    }
}
