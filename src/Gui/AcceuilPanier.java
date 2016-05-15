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
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author sawssen
 */
public class AcceuilPanier extends List implements CommandListener, Runnable{
 Command cmdRefresh = new Command("refresh", Command.SCREEN, 0);
//   StringItem stid=new StringItem("ID :",null);
//   StringItem stnom=new StringItem("NOM :",null);
//   StringItem stref=new StringItem("REFERENCE :",null);
//   StringItem stqt=new StringItem("QUANTITE:",null);
//   StringItem stprix=new StringItem("PRIX UNITAIRE:",null);
//   StringItem stprixTot=new StringItem("PRIX TOTAL:",null);
 /////
 HttpConnection ht;
    HttpConnection htt;
    DataInputStream dtt;
    DataInputStream dt;
    int x;
    int ch;
    ////////
    
 String list="";
 Command cmdacheter = new Command("Acheter", Command.OK, 0);
 ProduitPanierHandler handler = new ProduitPanierHandler();
 ProduitPanier[] prpan ;
 
 
    StringBuffer sb;
    Display disp;
    //Image img;
    
    
    public AcceuilPanier(String title, int listType, Display d) {
        super(title, listType);
        disp=d;
        Thread th = new Thread(this);
        th.start();
    }
    
    public void commandAction(Command c, Displayable d) {
        
        if (c == cmdRefresh) {
            //disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }
        if (c==cmdacheter)
        {  //Thread th2 = new Thread(new SendFacture(prpan,disp));
        //th2.prpan=prpan;
        
           // th2.start();
//            if (prpan.length > 0) {
//                for (int i = 0; i < prpan .length; i++) {
//                  
//                    list+="[{\"nom\":"+prpan[i].getNom()+", \"reference\":\""+prpan[i].getReference()+"\", \"quantite\":\""+Integer.toString(prpan[i].getQuantite())+"\", \"prix\":\""+Float.toString((float) prpan[i].getPrix())+"\"}]";
//                }
//            }
//        
//       //envoie
//           
       try{
           if (prpan.length > 0) {
               list="[";
                for (int i = 0; i < prpan .length; i++) {
                   
                    list+="{\"id\":\""+Integer.toString(prpan[i].getId())+"\", \"nom\":\""+prpan[i].getNom()+"\", \"reference\":\""+prpan[i].getReference()+"\", \"quantite\":\""+Integer.toString(prpan[i].getQuantite())+"\", \"prix\":\""+Float.toString((float) prpan[i].getPrix())+"\"}";
                    if(i!=prpan.length-1)
                        list+=",";
               }
                list+="]";
            }
            System.out.println("ici:"+list);
        String urlParameters="list="+list;
             //HttpConnection hc2 = (HttpConnection) Connector.open("http://localhost/sendMailFacture/mail/sendFact.php");
             HttpConnection outbound = (HttpConnection) Connector.open("http://localhost/sendMailFacture/mail/sendFact.php", Connector.READ_WRITE, false);
	outbound.setRequestMethod(HttpConnection.POST);
        outbound.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        outbound.setRequestProperty("Content-Length", "" +Integer.toString(urlParameters.getBytes().length));
	
        OutputStream out = outbound.openOutputStream();
	 out.write(urlParameters.getBytes());
            out.flush();   
	out.close();
	outbound.openInputStream().close();
//            htt = (HttpConnection) Connector.open("‪https://localhost/sendMailFacture/mail/sendFact.php?list="+list);
////                   
//        /*
//            dtt = htt.openDataInputStream();
//            InputStreamReader y = new InputStreamReader(dt, "UTF-8");
// 
//            while ((x = dtt.read())!= -1) 
//                {
//                System.out.println((char) x);
//                }*/
    } catch (IOException ex) {
            ex.printStackTrace();
        }
//        
  
 /////////////////////insersion dans la table facture
    }
                
    }

    public void run() {
        try {
            
          
            addCommand(cmdacheter);
              setCommandListener(this);
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsingPanier/select.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, handler);
            hc.close(); 
            // display the result
            prpan =handler.getProduitPanier();
            
	
	

            if (prpan.length > 0) {
                for (int i = 0; i < prpan .length; i++) {
                    //img=Image.createImage("");
                   // append();
                    append("NOM: "+prpan[i].getNom()+"\n"+"REFERENCE: "+prpan[i].getReference()+"\n"+"QUANTITE: "+Integer.toString(prpan[i].getQuantite())+"\n"+"PRIX UNITAIRE: "+Float.toString((float) prpan[i].getPrix())+"\n"+"PRIX Total: "+Float.toString((float) prpan[i].getPrix()*prpan[i].getQuantite())+"\n__________________________\n", null);
//                    append(prpan[i].getReference(), null);
//                    append(Float.toString((float) prpan[i].getPrix()), null);
                   // append("NOM: "+prpan[i].getNom(),null);
                     System.out.println("Hello111: ");
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    

    }


