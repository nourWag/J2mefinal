/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

//import foot2016.Midlet;
//import foot2016.dao.PersonneDAO;
//import foot2016.entities.Personne;
//import foot2016.handler.PersonneHandler;
import Entity.Personne;
import Handler.PersonneHandler;
import TunisiaMall.Midlet2;
import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author Snoussi
 */
public class PersonneList extends List implements CommandListener,Runnable {

    Display disp;
    List lst = new List("Projets", List.IMPLICIT);
   // Command cmdAjout = new Command("Ajouter un compte", Command.SCREEN, 0);
    Command cmdExit = new Command("Quitter", Command.EXIT, 0);
    Command cmdDel = new Command("Supprimer un compte", Command.SCREEN, 0);
    Command cmdMod = new Command("Modifier un compte", Command.SCREEN, 0);
    static int choice;
    StringBuffer sb = new StringBuffer();
    Personne[] projets;
    public PersonneList(){
        super("Liste des comptes", List.IMPLICIT);
//        addCommand(cmdAjout);
        addCommand(cmdExit);
        addCommand(cmdDel);
        addCommand(cmdMod);
        setCommandListener(this);
        Thread th = new Thread(this);
        th.start();
        
    }
//    public PersonneList(String title, int listType, Display d) {
//        super("Liste des comptes", List.IMPLICIT);
//        disp = d;
//        addCommand(cmdAjout);
//        addCommand(cmdExit);
//        addCommand(cmdDel);
//        addCommand(cmdMod);
//        setCommandListener(this);
//        Thread th = new Thread(this);
//        th.start();
//        
//        
//    }
    
    
    

    public void commandAction(Command c, Displayable d) {
//        if (c == cmdAjout) {
         //   Midlet.INSTANCE.disp.setCurrent(new AjoutForm());
      //  }
        if (c == cmdExit) {
            Midlet2.INSTANCE.notifyDestroyed();
        }
        if (c == cmdDel) {
            
            
            Midlet2.INSTANCE.disp.setCurrent(new DeleteForm());
        }
        if (c == cmdMod) {
            Midlet2.INSTANCE.disp.setCurrent(new menu_clien());
        }
    }

    public void run() {
            try {
         // this will handle our XML
            PersonneHandler projetsHandler = new PersonneHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:8000/PIDEVhamed/mobile/select.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, projetsHandler);
            // display the result
            projets = projetsHandler.getPersonne();
        
        Personne[] perso = new PersonneDAO().select();
        if (perso.length > 0) {
            for (int i = 0; i < perso.length; i++) {
                append(perso[i].getId()+ " - " +perso[i].getNom()+ " - " + perso[i].getPrenom()
                                        + " - " +perso[i].getUsername()+ " - " + perso[i].getMail()
                                        + " - " +perso[i].getPassword() + "\n ---------------------------------------- " , null);
            }
        }
    
              } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
    }
     private String showProjet(int i) {
        String res = "";
        if (projets.length > 0) {
            sb.append("* ");
            sb.append(projets[i].getNom());
            sb.append("\n");
            sb.append("* ");
            sb.append(projets[i].getPrenom());
            sb.append("\n");
            sb.append("* ");
            sb.append(projets[i].getMail());
            sb.append("\n");
            
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
}
    
