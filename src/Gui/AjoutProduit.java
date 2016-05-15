/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;

/**
 *
 * @author pc
 */
public class AjoutProduit implements CommandListener, ItemStateListener{



    Display disp ;
    //Form 1
    Form f1 = new Form("Ajouter Produit");
    Command cmBck = new Command("retour", Command.SCREEN, 0);
    private int defaultIndex=0;
    TextField tfprixachat = new TextField("Prix d'achat : ", null, 100, TextField.NUMERIC);
    TextField tfprixvente = new TextField("Prix de vente : ", null, 100, TextField.NUMERIC);
    TextField tfdes = new TextField("designation : ", null, 100, TextField.ANY);
    TextField tfmarque = new TextField("marque : ", null, 100, TextField.ANY);
    //TextField tfprixA = new TextField("prixA", null, 100, TextField.NUMERIC);
    //TextField tfprixV = new TextField("prixV", null, 100, TextField.NUMERIC);
    DateField tfDate =new DateField("Date ", DateField.DATE);
        TextField tfcouleur = new TextField("couleur", null, 100, TextField.ANY);
    
   
    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdBack = new Command("Retour", Command.BACK, 0);

    Form f2 = new Form("Produit ajouter avec succée");
    Form f3 = new Form("Erreur");

    Alert alerta = new Alert("Erreur", "DesolÃ©", null, AlertType.ERROR);

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost:8000/parsing/insert.php";
    StringBuffer sb = new StringBuffer();
    int ch;

    public AjoutProduit(Display disp) {
        this.disp = disp;
        this.startApp();
    }

    public void startApp() {
        
        
        f1.append(tfmarque);
        f1.append( tfdes);
        f1.append(tfprixachat);
        f1.append(tfprixvente);
        
        f1.append(tfDate);
  
    
    //defaultIndex = radioButtons.append("Femme", null);
   
       
     
        f1.addCommand(cmdValider);
        f1.addCommand(cmBck);
        f1.setCommandListener(this);
        f2.addCommand(cmdBack);
        f2.setCommandListener(this);
        f1.setItemStateListener(this);
        disp.setCurrent(f1);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdValider) {
             ajoutProduit();
        }
        if (c == cmdBack) {
            
            disp.setCurrent(f1);
        }
         if (c == cmBck) {
            
           // new MenuInscri(disp);
        }
    }

    public void  ajoutProduit() {
        Calendar calendar = Calendar.getInstance();
    calendar.setTime(tfDate.getDate());
                int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    try {
                hc = (HttpConnection) Connector.open(url+"?pa="+tfprixachat.getString().trim()+"&pv="+ tfprixvente.getString().trim()+"&designation="+ tfdes.getString().trim()+"&marque="+tfmarque.getString().trim()+"&date="+Integer.toString(day).trim()+"-"+Integer.toString(month).trim()+"-" + Integer.toString(year).trim());
              System.out.println(url+"?pa="+tfprixachat.getString().trim()+"&designation="+ tfdes.getString().trim()+"&marque="+tfmarque.getString().trim()+"&date="+Integer.toString(day).trim()+"-"+Integer.toString(month).trim()+"-" + Integer.toString(year).trim());  
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    disp.setCurrent(f2);
                }else{
                    disp.setCurrent(f3);
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

public void itemStateChanged(Item item) {
          
       // if(table[tfUser.getSelectedIndex()].equals("Administrateur")){f1.append(tfAdmin);}
    }}

