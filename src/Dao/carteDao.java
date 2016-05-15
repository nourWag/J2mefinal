/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Entity.cartefidelite;
import Handler.carteHandler;
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
 * @author lenovo
 */
public class carteDao {
    
    cartefidelite[] cartes;
    
    public boolean insert(cartefidelite crt){
        try {
            HttpConnection hc = (HttpConnection)Connector.open("http://localhost:8000/parsinglougein/insert1.php?nombrePointFidele="+crt.getNombrePointFidele()+"&dateCreationCarteFidelite="+crt.getDateCreationCarteFidelite());
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
    
    public boolean update(int idCarteFidelite, String nombrePointFidele, String dateCreationCarteFidelite){
        try {
            //System.out.println("Produit id"+pers.getId());
            HttpConnection hc = (HttpConnection)Connector.open("http://localhost:8000/parsinglougein/update1.php?idCarteFidelite="+idCarteFidelite+"&nombrePointFidele="+nombrePointFidele+"&dateCreationCarteFidelite="+dateCreationCarteFidelite);
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
    
    
   public cartefidelite[] select(){
       try {
            carteHandler crtHandler = new carteHandler();
            // get a parser object
            SAXParser SAXparser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:8000/parsinglougein/select1.php");//people.xml est un exemple
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
   
    public boolean delete(cartefidelite crt){
        try {
            HttpConnection hc = (HttpConnection)Connector.open("http://localhost:8000/parsinglougein/delete1.php?idCarteFidelite="+crt.getIdCarteFidelite());
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
