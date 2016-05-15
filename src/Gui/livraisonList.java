/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;




import Dao.livraisonDao;
import Entity.livraison;
import TunisiaMall.Midlet2;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

/**
 *
 * @author lenovo
 */
public class livraisonList extends List implements CommandListener,Runnable {

//    Command cmdAjout = new Command("Ajouter une carte", Command.SCREEN, 0);
    Command cmdExit = new Command("Quitter", Command.EXIT, 0);
    Command cmdDel = new Command("Supprimer une carte", Command.SCREEN, 0);
//    Command cmdMod = new Command("Modifier une carte", Command.SCREEN, 0);
    static int choice;

    public livraisonList() {
        super("Liste des livraisons", List.IMPLICIT);
//        addCommand(cmdAjout);
        addCommand(cmdExit);
        addCommand(cmdDel);
//        addCommand(cmdMod);
        setCommandListener(this);
        Thread th = new Thread(this);
        th.start();
        
    }

    public void commandAction(Command c, Displayable d) {
//        if (c == cmdAjout) {
//            Midlet22.INSTANCE.disp.setCurrent(new AjoutForm());
//        }
        if (c == cmdExit) {
            Midlet2.INSTANCE.notifyDestroyed();
        }
        if (c == cmdDel) {
            
            
            Midlet2.INSTANCE.disp.setCurrent(new DeleteForm());
        }
//        if (c == cmdMod) {
//            Midlet22.INSTANCE.disp.setCurrent(new UpdateForm());
//        }
    }

    public void run() {
        livraison[] crt = new livraisonDao().select();
        if (crt.length > 0) {
            for (int i = 0; i < crt.length; i++) {
                append(crt[i].getId()+ " - " +crt[i].getNom()+ " - " + crt[i].getPrenom()+ " - " +crt[i].getAdresse()+ " - " + crt[i].getTelephone()
                                        + "\n ---------------------------------------- " , null);
            }
        }
    }

}
