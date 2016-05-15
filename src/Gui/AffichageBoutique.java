/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import Handler.BoutiqueHandler;
import Entity.Boutique;
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
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
/**
 *
 * @author pc
 */
public class AffichageBoutique extends Form implements CommandListener, Runnable {

    Display disp;
    Command cmdList = new Command("Liste", Command.SCREEN, 0);
    Command cmdBack = new Command("Retour", Command.BACK, 0);
    Command cmdBack1 = new Command("Retour", Command.BACK, 0);
    Command cmdBack2 = new Command("Retour", Command.BACK, 0);
    Command cmdDelete = new Command("Supprimer", Command.SCREEN, 0);
    Command cmdModifier = new Command("Modifier", Command.SCREEN, 0);
    Command cmdValider = new Command("Valider", Command.SCREEN, 0);
    Boutique[] boutiques;
    List lst = new List("Boutiques", List.IMPLICIT); //deuscieme qui est une liste 
    Form f = new Form("Accueil");   //le premier form qui apparait
    Form form = new Form("Infos de Boutique"); //le troiscieme
    Form f1 = new Form("Modifier");
    Form loadingDialog = new Form("Chargement ");
    StringBuffer sb = new StringBuffer();
    int id;

    TextField tfId = new TextField("Id", null, 100, TextField.ANY);
    TextField tfNom = new TextField("Nom", null, 100, TextField.ANY);
    TextField tfcategorie = new TextField("categorie", null, 100, TextField.ANY);
    TextField tfFax = new TextField("Fax", null, 100, TextField.ANY);
    TextField tfNumeroTelephone = new TextField("Num", null, 100, TextField.ANY);
    TextField tfEmail = new TextField("Email", null, 100, TextField.ANY);
    TextField tfPromotion = new TextField("Promotion", null, 100, TextField.ANY);
  TextField tfDescription = new TextField("Description", null, 100, TextField.ANY);

    Ticker tickerModif = new Ticker("");
    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/parsingSalma/ajout.php";
    int ch;
    Alert alerta = new Alert("Erreur", "Desolée :( ", null, AlertType.ERROR);

    public AffichageBoutique(String title ,Display disp) {
        super(title);
        this.disp = disp;
        lst.setCommandListener(this);
        this.startApp();
    }

    public void startApp() {
        f.append("Appuyer sur Liste pour voir la liste");
        f.addCommand(cmdList);
        f.addCommand(cmdBack2);
        f.setCommandListener(this);
        
        lst.addCommand(cmdBack1);
        lst.setCommandListener(this);
        
        form.addCommand(cmdBack);
        form.addCommand(cmdDelete);
        form.addCommand(cmdModifier);
        form.setCommandListener(this);
        
        f1.append(tfNom);
        f1.append(tfcategorie);
        f1.append(tfFax);
        f1.append(tfNumeroTelephone);
        f1.append(tfEmail);
        f1.append(tfPromotion);
       f1.append(tfDescription);
        f1.addCommand(cmdValider);
        f1.setCommandListener(this);
        
        form.addCommand(cmdModifier);
        disp.setCurrent(f);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {

        if (c == cmdList) {
            disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }

        if (c == List.SELECT_COMMAND) {
            form.append("Informations sur boutique séléctionnée  : \n");
            form.append(showBoutique(lst.getSelectedIndex()));
            disp.setCurrent(form);
        }

        if (c == cmdBack) {
            form.deleteAll();
            disp.setCurrent(lst);
        }
        if (c == cmdBack1) {
            form.deleteAll();
            disp.setCurrent(f);
        }
        if (c == cmdBack2) {
            new Acceuil1(disp);
        }
        if (c == cmdDelete) {
            deleteBoutique();

        }
        if (c == cmdModifier) {
            disp.setCurrent(f1);

        }
        if (c == cmdValider) {
            disp.setCurrent(f1);
            updateBoutique(id);

        }

    }

    public void run() {
        try {//les threads
            // this will handle our XML
            BoutiqueHandler boutiqueHandler = new BoutiqueHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsingSalma/selectB.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, boutiqueHandler);
            // display the result
            boutiques = boutiqueHandler.getBoutiques();

            if (boutiques.length > 0) {
                for (int i = 0; i < boutiques.length; i++) {
                    lst.append(boutiques[i].getNom() + " " + boutiques[i].getCategorie() + boutiques[i].getFax() + " " + boutiques[i].getNumero_telephone() + boutiques[i].getEmail() + " " + boutiques[i].getPromotion()+ " " + boutiques[i].getDescription(), null);
                
        System.out.println(lst.append(boutiques[i].getNom() + " " + boutiques[i].getCategorie() + boutiques[i].getFax() + " " + boutiques[i].getNumero_telephone() + boutiques[i].getEmail() + " " + boutiques[i].getPromotion(), null));

                    
                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
    }

    private String showBoutique(int i) {
        String res = "";
        if (boutiques.length > 0) {
            sb.append("* ");
            sb.append(boutiques[i].getId());
            id = boutiques[i].getId();

            tfId.setString(String.valueOf(boutiques[i].getId()));
            sb.append("\n");
            sb.append(" ");

            sb.append(boutiques[i].getNom());
            tfNom.setString(boutiques[i].getNom());
            sb.append("\n");
            sb.append(" ");

            sb.append(boutiques[i].getCategorie());
            tfcategorie.setString(boutiques[i].getCategorie());
            sb.append("\n");

            sb.append(boutiques[i].getFax());
            tfFax.setString(boutiques[i].getFax());
            sb.append("\n");

            sb.append(boutiques[i].getEmail());
            tfEmail.setString(boutiques[i].getEmail());
            sb.append("\n");
            
            sb.append(boutiques[i].getPromotion());
            tfPromotion.setString(boutiques[i].getPromotion());
            sb.append("\n");
            
            sb.append(boutiques[i].getDescription());
            tfDescription.setString(boutiques[i].getDescription());
            sb.append("\n");

           
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public void deleteBoutique() {
        try {

            hc = (HttpConnection) Connector.open("http://localhost/parsingSalma/deleteB.php?id=" + id);
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equalsIgnoreCase(sb.toString().trim())) {

                alerta.setTitle("MERCI");
                alerta.setString("Boutique a ete supprimer avec succes");
                alerta.setTimeout(2000);
                alerta.setType(AlertType.INFO);
                sb = new StringBuffer("");
                disp.setCurrent(alerta);
            } else {
                alerta.setTitle("Erreur");
                System.out.println(sb.toString().trim());
                alerta.setString("désolée, suppression achoué" + sb.toString().trim());
                alerta.setType(AlertType.ERROR);
                alerta.setTimeout(2000);
                sb = new StringBuffer("");
                disp.setCurrent(alerta);
            }
            sb = new StringBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String updateBoutique(int id) {
        String res = "";
        if (boutiques.length > 0) {
            try {
                HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsingSalma/modifierB.php?id="+id+"&nom=" + tfNom.getString().trim() + "&categorie=" + tfcategorie.getString().trim() + "&Fax=" + tfFax.getString().trim() + "&numero_telephone=" + tfNumeroTelephone.getString().trim() + "&Email=" + tfEmail.getString().trim() + "&promotion=" + tfPromotion.getString().trim() + "&Description=" + tfDescription.getString().trim());

                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char) ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    disp.setCurrent(form);
                    tickerModif.setString("Modification du Boutique terminée");
                }
                sb = new StringBuffer("");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return res;
    }
}
