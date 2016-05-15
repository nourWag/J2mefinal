/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Entity.Personne;
import Handler.PersonneHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class PersonneDAO {
    
    Personne[] personnes;
    
    public boolean insert(Personne pers){
        try {
            HttpConnection hc = (HttpConnection)Connector.open("http://localhost:8000/PIDEVhamed/mobile/insert.php?nom="+pers.getNom()+"&prenom="+pers.getPrenom()+"&username="+pers.getUsername()+"&password="+pers.getPassword()+"&email="+pers.getMail());
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
    
    public boolean update(int id, String nom, String prenom, String username, String password, String email){
        try {
            //System.out.println("Produit id"+pers.getId());
            HttpConnection hc = (HttpConnection)Connector.open("http://localhost:8000/PIDEVhamed/mobile/update.php?id="+id+"&nom="+nom+"&prenom="+prenom+"&username="+username+"&password="+password+"&email="+email);
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
    
    
   public Personne[] select(){
       try {
            PersonneHandler persHandler = new PersonneHandler();
            // get a parser object
            SAXParser SAXparser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:8000/PIDEVhamed/mobile/select.php");//people.xml est un exemple
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            SAXparser.parse(dis, persHandler);
            // display the result
            personnes = persHandler.getPersonne();
             return personnes;
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

             return null;
   }
   
    public boolean delete(Personne pers){
        try {
            HttpConnection hc = (HttpConnection)Connector.open("http://localhost:8000/PIDEVhamed/mobile/delete.php?id="+pers.getId());
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
