/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import TunisiaMall.Midlet2;
import com.sun.lwuit.Button;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import java.io.IOException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author SASSOU
 */
public class menu_clien extends Form implements  ActionListener,CommandListener, Runnable{
    TextField sch = new TextField("Entrez l'id de l'utilisateur :", "", 50, TextField.ANY);
    TextField tf_nom = new TextField("nouveau nom", "", 50, TextField.ANY);
    TextField tf_prenom = new TextField("nouveau prenom", "", 50, TextField.ANY);
    TextField tf_username = new TextField("nouveau username", "", 50, TextField.ANY);
    TextField tf_password = new TextField("nouveau password", "", 50, TextField.ANY);
    TextField tf_email = new TextField("nouveau email", "", 50, TextField.ANY);
    Command cmdMod = new Command("Confirmer", Command.SCREEN, 0);
    Command cmdBack = new Command("Retour", Command.EXIT, 0);
    
    javax.microedition.lcdui.Display disp ;
    public menu_clien() {
        super("Modifier compte");

         Midlet2.INSTANCE.disp = disp;
         //init the LWUIT display
        com.sun.lwuit.Display.init(this);
        
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
            button3.setText("Gestion Produit");
            
            button3.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
             new Acceuil(Midlet2.INSTANCE.disp);
             }

});
            
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


    public void commandAction(Command c, Displayable d) {
          if (c == cmdBack) {
              Display disp = null;
            Midlet2.INSTANCE.disp.setCurrent(new PersonneList());
        }
        if (c == cmdMod) {
            Thread th = new Thread(this);
            th.start();
        }
    }

    public void run() {
         String id = sch.getString();
         int id1 = Integer.parseInt(id);
         
        String nom = tf_nom.getString();
        String prenom = tf_prenom.getString();
        String username = tf_username.getString();
        String password = tf_password.getString();
        String email = tf_email.getString();
        
        boolean result = new PersonneDAO().update(id1, nom, prenom, username, password, email);
        Alert alert = new Alert("Result");
        if (result) {
            alert.setType(AlertType.CONFIRMATION);
            alert.setString("Compte modifié avec succées!");
             Display disp = null;
            Midlet2.INSTANCE.disp.setCurrent(alert,new PersonneList());
        } else {
            alert.setType(AlertType.ERROR);
            alert.setString("ERROR!");
            Midlet2.INSTANCE.disp.setCurrent(alert);
        }
    }

    public void actionPerformed(ActionEvent ae) {
       
    }
    
}
