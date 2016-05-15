/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Dao.carteDao;
//import nn.MidletCarte;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import java.util.Calendar;
import javax.microedition.lcdui.DateField;

/**
 *
 * @author lenovo
 */
public class UpdateCarteForm extends Form implements CommandListener, Runnable{
    TextField sch = new TextField("Entrez l'id de la carte :", "", 50, TextField.ANY);
    TextField tf_nombre = new TextField("nouveau nombre", "", 50, TextField.ANY);
    DateField tf_date = new DateField("DateCreation", DateField.DATE);
    
    Command cmdMod = new Command("Confirmer", Command.SCREEN, 0);
    Command cmdBack = new Command("Retour", Command.EXIT, 0);
    public UpdateCarteForm() {
        super("Modifier carte");

        append(sch);
       
        append(tf_nombre);
        append(tf_date);
        
        
        addCommand(cmdMod);
        addCommand(cmdBack);
        setCommandListener(this);
        
    }


    public void commandAction(Command c, Displayable d) {
          if (c == cmdBack) {
//              MidletCarte.INSTANCE.disp.setCurrent(new carteList());
        }
        if (c == cmdMod) {
            Thread th = new Thread(this);
            th.start();
        }
    }

    public void run() {
         String id = sch.getString();
         int id1 = Integer.parseInt(id);
         
        String nombre = tf_nombre.getString();
        Calendar cal = Calendar.getInstance();
        cal.setTime(tf_date.getDate());
        String dateCrea = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
        
        
        boolean result = new carteDao().update(id1, nombre, dateCrea);
        Alert alert = new Alert("Result");
        if (result) {
            alert.setType(AlertType.CONFIRMATION);
            alert.setString("Carte modifié avec succées!");
//            MidletCarte.INSTANCE.disp.setCurrent(alert,new carteList());
        } else {
            alert.setType(AlertType.ERROR);
            alert.setString("ERROR!");
          //  MidletCarte.INSTANCE.disp.setCurrent(alert);
        }
    }
    
}
