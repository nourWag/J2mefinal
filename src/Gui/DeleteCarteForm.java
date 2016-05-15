/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Dao.carteDao;
import Entity.cartefidelite;
//import nn.MidletCarte;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;


/**
 *
 * @author lenovo
 */
public class DeleteCarteForm extends Form implements CommandListener, Runnable{
      TextField sch = new TextField("Entrer l'id de la carte à supprimer", "", 50, TextField.ANY);
  

    Command cmdDel = new Command("Confirmer", Command.SCREEN, 0);
    Command cmdBack = new Command("Retour", Command.EXIT, 0);

    public DeleteCarteForm(String title) {
        super(title);
    }
         public DeleteCarteForm() {
        super("Supprimer compte");
        
        append(sch);
        //append(sch_dest);
        addCommand(cmdDel);
        addCommand(cmdBack);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
           if (c == cmdBack) {
//               MidletCarte.INSTANCE.disp.setCurrent(new carteList());
        }
        if (c == cmdDel) {
            Thread th = new Thread(this);
            th.start();
        }
    }

    public void run() {
          int id1 = Integer.parseInt(sch.getString());
        //String dest = sch_dest.getString();
        
        boolean result = new carteDao().delete(new cartefidelite(id1));
        Alert alert = new Alert("Result");
        if (result) {
            alert.setType(AlertType.CONFIRMATION);
            alert.setString("Carte supprimé avec succées!");
//            MidletCarte.INSTANCE.disp.setCurrent(alert,new carteList());
        } else {
            alert.setType(AlertType.ERROR);
            alert.setString("ERROR!");
        //    MidletCarte.INSTANCE.disp.setCurrent(alert);
        }
    }
    
}
