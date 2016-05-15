/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Dao.carteDao;
import Entity.cartefidelite;
//import nn.MidletCarte;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author lenovo
 */
public class AjoutCarteForm extends Form implements CommandListener, Runnable {

    TextField tf_nombre = new TextField("nombrePointFidelite", "", 50, TextField.ANY);
    DateField datecreation = new DateField("DateCreation", DateField.DATE);
    

    Command cmdEnregistrer = new Command("Enregistrer", Command.SCREEN, 0);
    Command cmdBack = new Command("Retour", Command.EXIT, 0);

        
    public AjoutCarteForm() {
        super("Inscription");

        append(tf_nombre);
        append(datecreation);
        
        addCommand(cmdEnregistrer);
        addCommand(cmdBack);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
//            MidletCarte.INSTANCE.disp.setCurrent(new carteList());
        }
        if (c == cmdEnregistrer) {
            Thread th = new Thread(this);
            th.start();
        }

    }

    public void run() {
        String st_nombre = tf_nombre.getString();
        Calendar cal = Calendar.getInstance();
        cal.setTime(datecreation.getDate());
        String dateCrea = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
        
        
        boolean result = new carteDao().insert(new cartefidelite(st_nombre, dateCrea));
        Alert alert = new Alert("Résultat");
        if (result) {
            alert.setType(AlertType.CONFIRMATION);
            alert.setString("carte ajoutée avec succés");
//            MidletCarte.INSTANCE.disp.setCurrent(alert,new carteList());
        } else {
            alert.setType(AlertType.ERROR);
            alert.setString("Ajout de carte échoué");
//            MidletCarte.INSTANCE.disp.setCurrent(alert);
        }
    }

}
