/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Handler.ProduitPanierHandler;
import Entity.ProduitPanier;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;

/**
 *
 * @author sawssen
 */
public class SendFacture  implements  Runnable{

    DataInputStream dtt;
    DataInputStream dt;
    int x;
    int ch;
    Display disp;
    String list="";
    
    ProduitPanierHandler handler = new ProduitPanierHandler();
    public ProduitPanier[] prpan ;
    
    public SendFacture(ProduitPanier[] prpan,Display d) {
        //super(title);
        disp=d;
        this.prpan=prpan;
    }
public void setpr()
{

}
//    public void commandAction(Command c, Displayable d) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public void run() {
    if (prpan.length > 0) {
                for (int i = 0; i < prpan .length; i++) {
                  
                    list+="[{\"nom\":"+prpan[i].getNom()+", \"reference\":\""+prpan[i].getReference()+"\", \"quantite\":\""+Integer.toString(prpan[i].getQuantite())+"\", \"prix\":\""+Float.toString((float) prpan[i].getPrix())+"\"}]";
                }
            }
    System.out.println(list);
    try{
           HttpConnection htt = (HttpConnection) Connector.open("‪http://localhost/sendMailFacture/mail/sendFact.php?list="+list);
   



//                   
        /*
            dtt = htt.openDataInputStream();
            InputStreamReader y = new InputStreamReader(dt, "UTF-8");
 
            while ((x = dtt.read())!= -1) 
                {
                System.out.println((char) x);
                }*/
        } catch (IOException ex) {
            ex.printStackTrace();
        }   
}
}

