/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Entity.livraison;
import Handler.livraisonHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 *
 * @author Saafi_Amine
 */
public class livraisonDao {
    
    livraison[] cartes;
    
//    public boolean insert(livraison crt){
//        try {
//            HttpConnection hc = (HttpConnection)Connector.open("http://localhost/parsing/insert1.php?nombrePointFidele="+crt.getId()+"&dateCreationCarteFidelite="+crt.getDateCreationCarteFidelite());
//            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
//           StringBuffer sb = new StringBuffer();
//           int ch;
//            while ((ch = dis.read())!=-1) {
//                sb.append((char)ch);                
//            }
//            if (sb.toString().trim().equals("success")) {
//                return true;
//            }
//            
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }
    
//    public boolean update(int idCarteFidelite, String nombrePointFidele, String dateCreationCarteFidelite){
//        try {
//            //System.out.println("Produit id"+pers.getId());
//            HttpConnection hc = (HttpConnection)Connector.open("http://localhost/parsing/update1.php?idCarteFidelite="+idCarteFidelite+"&nombrePointFidele="+nombrePointFidele+"&dateCreationCarteFidelite="+dateCreationCarteFidelite);
//            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
//           StringBuffer sb = new StringBuffer();
//           int ch;
//            while ((ch = dis.read())!=-1) {
//                sb.append((char)ch);                
//            }
//            if (sb.toString().trim().equals("success")) {
//                return true;
//            }
//            
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }
//    
    
   public livraison[] select(){
       try {
            livraisonHandler crtHandler = new livraisonHandler();
            // get a parser object
            SAXParser SAXparser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing/select2.php");//people.xml est un exemple
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            SAXparser.parse(dis, crtHandler);
            // display the result
            cartes = crtHandler.getMatch();
             return cartes;
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

             return null;
   }
   
    public boolean delete(livraison crt){
        try {
            HttpConnection hc = (HttpConnection)Connector.open("http://localhost/parsing/delete2.php?id="+crt.getId());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
           StringBuffer sb = new StringBuffer();
           int ch;
            while ((ch = dis.read())!=-1) {
                sb.append((char)ch);                
            }
            if (sb.toString().trim().equals("success")) {
                return true;
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
