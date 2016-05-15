/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;


import Entity.cartefidelite;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *CarteHandler
 * @author Saafi_Amine
 */
public class carteHandler extends DefaultHandler {

    private Vector personneVector;

    public carteHandler() {
        personneVector = new Vector();
    }

    public cartefidelite[] getMatch() {
        cartefidelite[] personTab = new cartefidelite[personneVector.size()];
        personneVector.copyInto(personTab);
        return personTab;
    }

    String selectedBalise = "";
    cartefidelite seclectedPersonne;

    public void startElement(String string, String string1, String qName, Attributes atrbts) throws SAXException {
        if (qName.equals("cartefidelite")) {
            seclectedPersonne = new cartefidelite();
        } else if (qName.equals("idCarteFidelite")) {
            selectedBalise = "idCarteFidelite";
        } else if (qName.equals("nombrePointFidele")) {
            selectedBalise = "nombrePointFidele";
        } else if (qName.equals("dateCreationCarteFidelite")) {
            selectedBalise = "dateCreationCarteFidelite";
        } 
    }

    public void endElement(String string, String string1, String qName) throws SAXException {
        if (qName.equals("cartefidelite")) {

            personneVector.addElement(seclectedPersonne);
            seclectedPersonne = null;
        } else if (qName.equals("idCarteFidelite")) {
            selectedBalise = "";
        } else if (qName.equals("nombrePointFidele")) {
            selectedBalise = "";
        } else if (qName.equals("dateCreationCarteFidelite")) {
            selectedBalise = "";
        } 
        
    }

    public void characters(char[] chars, int i, int i1) throws SAXException {
        if (seclectedPersonne != null) {
            if (selectedBalise.equals("idCarteFidelite")) {
                seclectedPersonne.setIdCarteFidelite(Integer.parseInt(new String(chars, i, i1)));
                System.out.println(new String(chars, i, i1));
            }
            if (selectedBalise.equals("nombrePointFidele")) {
                seclectedPersonne.setNombrePointFidele(new String(chars, i, i1));
                System.out.println(new String(chars, i, i1));
            }
            if (selectedBalise.equals("dateCreationCarteFidelite")) {
                seclectedPersonne.setDateCreationCarteFidelite(new String(chars, i, i1));
                System.out.println(new String(chars, i, i1));
            }
            
        }
    }

}
