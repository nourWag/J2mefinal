/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;


import Entity.Personne;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class PersonneHandler extends DefaultHandler {

    private Vector personneVector;

    public PersonneHandler() {
        personneVector = new Vector();
    }

    public Personne[] getPersonne() {
        Personne[] personTab = new Personne[personneVector.size()];
        personneVector.copyInto(personTab);
        return personTab;
    }

    String selectedBalise = "";
    Personne seclectedPersonne;

    public void startElement(String string, String string1, String qName, Attributes atrbts) throws SAXException {
        if (qName.equals("fos_user")) {
            seclectedPersonne = new Personne();
        } else if (qName.equals("id")) {
            selectedBalise = "id";
        } else if (qName.equals("nom")) {
            selectedBalise = "nom";
        } else if (qName.equals("prenom")) {
            selectedBalise = "prenom";
        } else if (qName.equals("username")) {
            selectedBalise = "username";
       
        } else if (qName.equals("email")) {
            selectedBalise = "email";
        }
    }

    public void endElement(String string, String string1, String qName) throws SAXException {
        if (qName.equals("fos_user")) {

            personneVector.addElement(seclectedPersonne);
            seclectedPersonne = null;
        } else if (qName.equals("id")) {
            selectedBalise = "";
        } else if (qName.equals("nom")) {
            selectedBalise = "";
        } else if (qName.equals("prenom")) {
            selectedBalise = "";
        } else if (qName.equals("username")) {
            selectedBalise = "";
        
        } else if (qName.equals("email")) {
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
            if (selectedBalise.equals("username")) {
                seclectedPersonne.setUsername(new String(chars, i, i1));
                System.out.println(new String(chars, i, i1));
            }
            
            if (selectedBalise.equals("email")) {
                seclectedPersonne.setMail(new String(chars, i, i1));
                System.out.println(new String(chars, i, i1));
            }
        }
    }

}
