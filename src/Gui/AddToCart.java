/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import TunisiaMall.Midlet2;

/**
 *
 * @author sawssen
 */
public class AddToCart extends Form implements CommandListener{
 TextField tfidPr = new TextField("ID du Produit", "", 10, TextField.ANY);
    TextField tfidPan = new TextField("Id du panier", "", 10, TextField.ANY);
    TextField tfQuantite = new TextField("Quantite", "", 10, TextField.ANY);

//
StringItem stidPr=new StringItem("ID du Produit :","12");
StringItem stidPan=new StringItem("ID du Panier :","1");
StringItem stNom=new StringItem("NOM :", "robe");
StringItem stRef=new StringItem("Référence :", "fdgl");
StringItem stPrixU=new StringItem("Prix unitaire:","120");
String[] tab1={"1","2","3","4","5","6"};
ChoiceGroup chg2=new ChoiceGroup("Quantité", ChoiceGroup.POPUP,tab1, null);

    Command cmdNext = new Command("Ajouter", Command.OK, 0);
    Command cmdPan = new Command("Panier", Command.OK, 0);
 HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb ;
    String url="http://localhost/parsingPanier/insertPanier.php?"; 
    public AddToCart(String title) {
        super(title);
//        append(tfidPr);
//        append(tfidPan);
//        append(tfQuantite);
        
         append(stNom);
        append(stRef);
        append(chg2);
        append(stPrixU);
        addCommand(cmdNext);
        addCommand(cmdPan);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if(c==cmdNext){
        try {
            String idPr, idPan,quantite;
//            idPr= tfidPr.getString();
//            idPan = tfidPan.getString();
//            quantite = tfQuantite.getString();
            String choix="";
            idPr=stidPr.getText();
            idPan=stidPan.getText();
            for (int i = 0; i < chg2.size(); i++) {
               if(chg2.isSelected(i)) 
               choix+=chg2.getString(i);
               quantite=choix;
            }
            quantite=choix;
           // quantite = tfQuantite.getString();
            hc=(HttpConnection)Connector.
                    open(url+"idPr="+idPr+"&idPan="+idPan+"&quantite="+quantite);
       dis=hc.openDataInputStream();
           
       int ascii;
       sb =new  StringBuffer();
       
       while( (ascii=dis.read()) != -1 ){
           
           sb.append((char)ascii);
           
       }
       
       
       if(sb.toString().equals("successfully added")){
           Alert a= new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
           a.setTimeout(3000);
           Midlet2.INSTANCE.disp.setCurrent(a);
       }else{
           Alert a= new Alert("Information", sb.toString(), null, AlertType.ERROR);
           a.setTimeout(3000);
          Midlet2.INSTANCE.disp.setCurrent(a);
       }
       
      
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     
        
       
        
    } 
        if(c==cmdPan)
        {Midlet2.INSTANCE.disp.setCurrent(new AcceuilPanier("Mon Panier", List.IMPLICIT, Midlet2.INSTANCE.disp));
        }
        
    }
    
}
