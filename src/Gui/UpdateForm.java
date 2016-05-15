/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import TunisiaMall.Midlet2;
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
public class UpdateForm extends Form implements CommandListener, Runnable{
    TextField sch = new TextField("Entrez l'id de l'utilisateur :", "", 50, TextField.ANY);
    TextField tf_nom = new TextField("nouveau nom", "", 50, TextField.ANY);
    TextField tf_prenom = new TextField("nouveau prenom", "", 50, TextField.ANY);
    TextField tf_username = new TextField("nouveau username", "", 50, TextField.ANY);
    TextField tf_password = new TextField("nouveau password", "", 50, TextField.ANY);
    TextField tf_email = new TextField("nouveau email", "", 50, TextField.ANY);
    Command cmdMod = new Command("Confirmer", Command.SCREEN, 0);
    Command cmdBack = new Command("Retour", Command.EXIT, 0);
    public UpdateForm() {
        super("Modifier compte");

        append(sch);
       
        append(tf_nom);
        append(tf_prenom);
        append(tf_username);
        append(tf_password);
        append(tf_email);
        
        addCommand(cmdMod);
        addCommand(cmdBack);
        setCommandListener(this);
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
    
}
