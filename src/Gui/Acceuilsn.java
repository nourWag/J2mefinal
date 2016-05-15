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
public class Acceuilsn  implements  CommandListener,ActionListener{
    StringItem st=new StringItem("message : ", null);
    TextField txt =new TextField("jour", null, 50, TextField.ANY);
    String[] choix=new String[]{"imsek","iftar"};
    ChoiceGroup chG=new ChoiceGroup("compétence", ChoiceGroup.POPUP, choix, null); 
   
   
    Image img;
    HttpConnection http;
    DataInputStream data;
   
    
    
    Command cmd =new Command("connexion", Command.OK, 0);
    
    javax.microedition.lcdui.Display disp ;
    Displayable displ;
    public Acceuilsn(javax.microedition.lcdui.Display disp) {
     
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
            button2.setText("Gestion Boutique ");
            button2.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
             new Acceuil1(Midlet2.INSTANCE.disp);
             }

});
            mainForm.addComponent(button2);
          
            com.sun.lwuit.Image icon2 = com.sun.lwuit.Image.createImage("/icons/19.png");
            Button button3=new Button(icon2);

    button3.setText("Gestion Produit ");
            button3.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
             new Acceuil(Midlet2.INSTANCE.disp);
             
            }

});        
            mainForm.addComponent(button3);
            
            
             
          
            com.sun.lwuit.Image icon4 = com.sun.lwuit.Image.createImage("/icons/19.png");
            Button button4=new Button(icon2);
            button4.setText(" Modification de compte ");
            button4.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
              Midlet2.INSTANCE.disp.setCurrent(new UpdateForm());
             
            }

});        
            mainForm.addComponent(button4);

 com.sun.lwuit.Image icon5 = com.sun.lwuit.Image.createImage("/icons/19.png");
            Button button5=new Button(icon5);
            button5.setText(" Gestion carte fidelite ");
            button5.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
              Midlet2.INSTANCE.disp.setCurrent(new carteList());
             
            }
             
             
             

});        
            mainForm.addComponent(button5);

            
             com.sun.lwuit.Image icon6 = com.sun.lwuit.Image.createImage("/icons/19.png");
            Button button6=new Button(icon6);
            button6.setText(" Gestion Livraison ");
            button6.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
              Midlet2.INSTANCE.disp.setCurrent(new livraisonList());
             
            }

});        
            mainForm.addComponent(button6);
            
            
            
            
               com.sun.lwuit.Image icon7 = com.sun.lwuit.Image.createImage("/icons/19.png");
            Button button7=new Button(icon7);
            button7.setText(" Statistique par annee ");
            button7.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
              Midlet2.INSTANCE.disp.setCurrent(new Statibatton());
             
            }

});        
            mainForm.addComponent(button7);
            
            
            
            
               com.sun.lwuit.Image icon8 = com.sun.lwuit.Image.createImage("/icons/19.png");
            Button button8=new Button(icon8);
            button8.setText(" Statistique par nombre de points ");
            button8.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
              Midlet2.INSTANCE.disp.setCurrent(new PieCarteChart());
             
            }

});        
            mainForm.addComponent(button8);
            
            
              com.sun.lwuit.Image icon9 = com.sun.lwuit.Image.createImage("/icons/19.png");
            Button button9=new Button(icon9);
            button9.setText(" Statistique generale ");
            button9.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
                 int[] data = {60, 60, 180, 60};
              Midlet2.INSTANCE.disp.setCurrent(new PieChartCanvas(data));
             
            }

});        
            mainForm.addComponent(button9);
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
 Midlet2.INSTANCE.startApp();
    }
}
