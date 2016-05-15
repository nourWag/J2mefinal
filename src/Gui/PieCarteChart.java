/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.cartefidelite;
import Handler.carteHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;




/**
 *
 * @author lougein
 */
public class PieCarteChart extends Canvas implements CommandListener{

    cartefidelite[] carte;
    int colors[]={199995,60000,4557568,65535,000015232,2999900,2631007,9000689,8519755,2631720};
    public PieCarteChart(){
        try {
                    try {
            carteHandler hand = new carteHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
             HttpConnection hc = (HttpConnection) Connector.open("http://localhost:8000/parsinglougein/select1.php");//people.xml est un exemple
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis,hand);
            this.carte = hand.getMatch();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            carteHandler carteHandler = new carteHandler();
            
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:8000/parsinglougein/stats.php");//people.xml est un exemple
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, carteHandler);
            carte =new cartefidelite[carteHandler.getMatch().length] ;
            carteHandler.getMatch();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    protected void paint(Graphics g) {
        g.setColor(255,255,255);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < carte.length; i++) {
            System.out.println(carte[i]);
        }
        for (int i = 0; i < carte.length; i++) {
            g.setColor(255,0,0);
            
            
        }
        
        int ca=0,cy=230;
        for (int i = 0; i < 20; i++) {
            g.setColor(colors[i]);
            g.fillArc(getWidth()/2-100, 10, 200, 200, ca,pourcentage(carte[i].getNombrePointFidele()));
            g.fillRect(2, cy, 10, 10);
            g.drawString(carte[i].getNombrePointFidele(),15 , cy-3,0);
            ca+=pourcentage(carte[i].getNombrePointFidele());
            cy+=15;
            
        }
    }
    public int pourcentage(String etat){
        int counter=0;
        for (int i = 0; i < carte.length; i++) {
            if (carte[i].getNombrePointFidele().equals(etat))
                counter++; 
            
        }
        return counter*360/carte.length;
    }

    public void commandAction(Command c, Displayable d) {
    }

}
