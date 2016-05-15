/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Entity.Personne;
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


public class DeleteForm extends Form implements CommandListener, Runnable{
      TextField sch = new TextField("Entrer l'id du compte à supprimer", "", 50, TextField.ANY);
  

    Command cmdDel = new Command("Confirmer", Command.SCREEN, 0);
    Command cmdBack = new Command("Retour", Command.EXIT, 0);

    public DeleteForm(String title) {
        super(title);
    }
         public DeleteForm() {
        super("Supprimer compte");
        
        append(sch);
        //append(sch_dest);
        addCommand(cmdDel);
        addCommand(cmdBack);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
           if (c == cmdBack) {
               Display disp = null;
            Midlet2.INSTANCE.disp.setCurrent(new PersonneList());
        }
        if (c == cmdDel) {
            Thread th = new Thread(this);
            th.start();
        }
    }

    public void run() {
          
          String id = sch.getString();
          int id1 = Integer.parseInt(id);
        //String dest = sch_dest.getString();
        
        boolean result = new PersonneDAO().delete(new Personne(id1));
        Alert alert = new Alert("Result");
        if (result) {
            alert.setType(AlertType.CONFIRMATION);
            alert.setString("Compte supprimé avec succées!");
              Display disp = null;
            Midlet2.INSTANCE.disp.setCurrent(alert,new PersonneList());
        } else {
            alert.setType(AlertType.ERROR);
            alert.setString("ERROR!");
            Midlet2.INSTANCE.disp.setCurrent(alert);
        }
    }
    
}
