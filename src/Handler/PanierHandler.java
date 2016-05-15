package Handler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Entity.ProduitPanier;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class PanierHandler extends DefaultHandler {
    // this will hold all the data we read
    private Vector panierVector;
 
    public PanierHandler() {
        panierVector = new Vector();
    }
 
    public ProduitPanier[] getPeople() {
        ProduitPanier[] pTab = new ProduitPanier[panierVector.size()];
        panierVector.copyInto(pTab);
        return pTab;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
    private ProduitPanier currentPerson;
//    private PhoneNumber currentPhoneNumber;
 
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("panier")) {
		    // create new Person object
            
            // "attributes" holds name and value pairs from inside the tag
            String id = attributes.getValue("id");
            String nom = attributes.getValue("nom");
            String refernce = attributes.getValue("reference");
            String prix = attributes.getValue("prix");
            currentPerson = new ProduitPanier();
//            if (givenName == null || familyName == null) {
//                throw new IllegalArgumentException("Person requires both givenname and familyname");
//            }
            
//        } else if (qName.equals("phone")) {
//            
//            currentPhoneNumber = new PhoneNumber();
//            String type = attributes.getValue("type");
//            
//            currentPhoneNumber.setType(type);
//        
        }
    }
 
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("person")) {
            // add completed Person object to collection
            panierVector.addElement(currentPerson);
            
            // we are no longer processing a <person.../> tag
            currentPerson = null;
//        } else if (qName.equals("phone")) {
//            // add completed PhoneNumber object to current Person
//            currentPerson.addPhoneNumber(currentPhoneNumber);
//            // we are no longer processing a <phone.../> tag
//            currentPhoneNumber = null;
        }
    }
 
    // "characters" are the text inbetween tags
    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
//      if (currentPerson != null) {
//////// 
//    }
    }
}
