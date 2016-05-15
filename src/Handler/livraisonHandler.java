/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;


import Entity.livraison;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *CarteHandler
 * @author 
 */
public class livraisonHandler extends DefaultHandler {

    private Vector personneVector;

    public livraisonHandler() {
        personneVector = new Vector();
    }

    public livraison[] getMatch() {
        livraison[] personTab = new livraison[personneVector.size()];
        personneVector.copyInto(personTab);
        return personTab;
    }

    String selectedBalise = "";
    livraison seclectedPersonne;

    public void startElement(String string, String string1, String qName, Attributes atrbts) throws SAXException {
        if (qName.equals("liraisonf")) {
            seclectedPersonne = new livraison();
        } else if (qName.equals("id")) {
            selectedBalise = "id";
        } else if (qName.equals("nom")) {
            selectedBalise = "nom";
        } else if (qName.equals("prenom")) {
            selectedBalise = "prenom";
        } else if (qName.equals("adresse")) {
            selectedBalise = "adresse";
        }  else if (qName.equals("telephone")) {
            selectedBalise = "telephone";
        }  
    }

    public void endElement(String string, String string1, String qName) throws SAXException {
        if (qName.equals("liraisonf")) {

            personneVector.addElement(seclectedPersonne);
////            seclectedPersonne = null;
        } else if (qName.equals("id")) {
            selectedBalise = "";
        } else if (qName.equals("nom")) {
            selectedBalise = "";
        } else if (qName.equals("prenom")) {
            selectedBalise = "";
        } else if (qName.equals("adresse")) {
            selectedBalise = "";
        }else if (qName.equals("telephone")) {
            selectedBalise = "";
        
    }
    }

    public void characters(char[] chars, int i, int i1) throws SAXException {
        if (seclectedPersonne != null) {
            if (selectedBalise.equals("id")) {
                seclectedPersonne.setId(Integer.parseInt(new String(chars, i, i1)));
                System.out.println(new String(chars, i, i1));
            }
            if (selectedBalise.equals("nom")) {
                seclectedPersonne.setNom(new String(chars, i, i1));
                System.out.println(new String(chars, i, i1));
            }
            if (selectedBalise.equals("prenom")) {
                seclectedPersonne.setPrenom(new String(chars, i, i1));
                System.out.println(new String(chars, i, i1));
            }
             if (selectedBalise.equals("adresse")) {
                seclectedPersonne.setAdresse(new String(chars, i, i1));
                System.out.println(new String(chars, i, i1));
            }
              if (selectedBalise.equals("telephone")) {
                seclectedPersonne.setTelephone(new String(chars, i, i1));
                System.out.println(new String(chars, i, i1));
            }
        }
    }

}
