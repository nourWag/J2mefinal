/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Handler.ProduitHandler;
import Entity.Produit;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
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
 * @author Houssem Eddine
 */
public class AffichageProduit  implements CommandListener, Runnable {

    Display disp ;
    Command cmdList = new Command("Liste", Command.SCREEN, 0);
    Command cmdBack = new Command("Retour", Command.BACK, 0);
    Command cmdBack1 = new Command("Retour", Command.BACK, 0);
    Command cmdBack2 = new Command("Retour", Command.BACK, 0);
    Command cmdDelete = new Command("Supprimer", Command.SCREEN, 0);
    Command cmdModifier = new Command("Modifier", Command.SCREEN, 0);
     Command cmdValider = new Command("Valider", Command.SCREEN, 0);
    Produit[] users;
    List lst = new List("Produits", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form form = new Form("Infos de Produit");
    Form f1 = new Form("Modifier");
    Form loadingDialog = new Form("Chargement ");
    StringBuffer sb = new StringBuffer();
   int id;
    
    TextField tfPrixAchta = new TextField("Prix d'achat : ", null, 100, TextField.NUMERIC);
    TextField tfPrixVent = new TextField("Prix de vente : ", null, 100, TextField.NUMERIC);
    TextField tfmarque  = new TextField("marque : ", null, 100, TextField.ANY);
    TextField tfdesignation = new TextField("designation : ", null, 100, TextField.ANY);
    DateField tfDate =new DateField("Date d'ajout ", DateField.DATE);
    //ChoiceGroup radioButtons = new ChoiceGroup("Sexe", Choice.EXCLUSIVE);
    //TextField tfEmail = new TextField("Email", null, 100, TextField.EMAILADDR);
    //TextField tfTel=new TextField("telephone", null, 100, TextField.NUMERIC);
 //String[] table={"Medecin","Stagiaire","Secretaire","Benevole","Administrateur"};
   // ChoiceGroup tfUser =new ChoiceGroup("Type utilisateur", ChoiceGroup.POPUP, table, null);
    String[] table1={"R.H","Finance","Infrastructure","Stock"};
    ChoiceGroup tfAdmin =new ChoiceGroup("Type Admin", ChoiceGroup.POPUP, table1, null);
Ticker tickerModif=new Ticker("");
     //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/Parse/ajout.php";
//    StringBuffer sb = new StringBuffer();
    int ch;
    Alert alerta = new Alert("Erreur","Desolée :( ",null,AlertType.ERROR);

    public AffichageProduit(Display disp) {
        this.disp = disp;
        this.startApp();
    }
    
    public void startApp() {
        f.append("Appuyer sur Liste pour voir la liste");
        f.addCommand(cmdList);
        f.addCommand( cmdBack2);
        f.setCommandListener(this);
        lst.addCommand(cmdBack1);
        lst.setCommandListener(this);
        form.addCommand(cmdBack);
        form.addCommand(cmdDelete);
        form.addCommand(cmdModifier);
        form.setCommandListener(this);
       
        f1.append(tfmarque);
        f1.append(tfdesignation);
         f1.append(tfPrixAchta);
        f1.append(tfPrixVent);
         f1.append(tfDate);
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
            form.append("Informations sur produit séléctionnée  : \n");
            form.append(showProduit(lst.getSelectedIndex()));
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
            new Acceuil(disp);
        }
          if (c == cmdDelete) {
            
                 deleteProduit();
            
        }
          if (c == cmdModifier) {
             
              disp.setCurrent(f1);
               
               
        }
          if (c == cmdValider) {
             
              disp.setCurrent(f1);
               updateProduit(id);
               
        }

    }

    public void run() {
        try {//les threads
            // this will handle our XML
            ProduitHandler userHandler = new ProduitHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:8000/parsing/select.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, userHandler);
            // display the result
            users = userHandler.getProduits();

            if (users.length > 0) {
                for (int i = 0; i < users.length; i++) {
                    lst.append("marque : "+users[i].getMarque()+" nom de produit : "
                            +users[i].getDesignation()+" ------------------------------------------\n ", null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
    }

    private String showProduit(int i) {
        String res = "";
        if (users.length > 0) {
            
             sb.append("* ");
            sb.append("nom de produit   : ");
            sb.append(users[i].getDesignation());
            
            sb.append(" ");
             sb.append("marque : ");
            sb.append(users[i].getMarque());
            id=users[i].getID();
           
            String s=String.valueOf(users[i].getPrixdachat());
//            tfPrixAchta.setString(s);
            sb.append("\n");
           
            sb.append("* ");
            sb.append("Prix d'achat : ");
            sb.append(users[i].getPrixdachat());
            sb.append("\n");
           
            sb.append("* ");
            sb.append("Prix de vente  : ");
            sb.append(users[i].getPrixvente());
            sb.append("\n");
           
            sb.append("* ");
            sb.append("description  : ");
            sb.append(users[i].getDescription());
            sb.append("\n");
           
           
          //  tfPrixVent.setString(String.valueOf(users[i].getPrixdachat()));
           /* sb.append("\n");
            sb.append("* ");
            sb.append(users[i].getMarque());
          //  tfdesignation.setString(users[i].getMarque());
            sb.append("\n");
            sb.append("* ");
            sb.append(users[i].getDesignation());
           // tfmarque.setString(users[i].getMarque());
            sb.append("\n");*/
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
   public void deleteProduit() {
        try {
            
                hc = (HttpConnection) Connector.open("http://localhost:8000/parsing/delete.php?id="+id);

                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equalsIgnoreCase(sb.toString().trim())) {
                
                alerta.setTitle("MERCI");
                alerta.setString("le produit a ete supprimer avec succes");
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
    private String updateProduit(int id)  {
        String res = "";
        Calendar calendar = Calendar.getInstance();
    calendar.setTime(tfDate.getDate());
                int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (users.length > 0) {
            try {
                HttpConnection hc = (HttpConnection) Connector.open("http://localhost:8000/parsing/modifier.php?id="+id+"&designation=" + tfdesignation.getString().trim() + "&marque=" + tfmarque.getString().trim()+"&pa="+tfPrixAchta.getString().trim()+"&pv="+tfPrixVent.getString().trim()+"&date=" + Integer.toString(day).trim() + "-" + Integer.toString(month).trim() + "-" + Integer.toString(year).trim());
                System.out.println("http://localhost:8000/parsing/modifier.php?id="+id+"&designation=" + tfdesignation.getString().trim() + "&marque=" + tfmarque.getString().trim() +"&date=" + Integer.toString(day).trim() + "-" + Integer.toString(month).trim() + "-" + Integer.toString(year).trim());
                
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char) ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    disp.setCurrent(form);
                     tickerModif.setString("Modification du Produit :  est terminée");
                }
                sb = new StringBuffer("");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return res;
    }}