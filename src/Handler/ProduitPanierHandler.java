package Handler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import Entity.ProduitPanier;


public class ProduitPanierHandler extends DefaultHandler {
   private Vector panierVector;
 
    public ProduitPanierHandler() {
        panierVector = new Vector();
    }
 
    public ProduitPanier[] getProduitPanier() {
        ProduitPanier[] prTab = new ProduitPanier[panierVector.size()];
        panierVector.copyInto(prTab);
        return  prTab ;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
    private ProduitPanier currentProduct;
    private String currentTag;
 
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("produitpanier")) {
            currentProduct = new ProduitPanier();
        
            
        } 
        else if (qName.equals("id")) {
            
            currentTag="id";
           
        }
        else if (qName.equals("nom")) {
            
             currentTag="nom";
           
        }
        else if (qName.equals("ref")) {
            
             currentTag="ref";
           
        }
        else if (qName.equals("quantite")) {
            
             currentTag="quantite";
           
        }
         else if (qName.equals("prix")) {
            
             currentTag="prix";
           
        }
    }
 
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("produitpanier")) {
            // add completed Person object to collection
            panierVector.addElement(currentProduct);
            System.out.println("Hello"+currentProduct.getNom());
            
            // we are no longer processing a <person.../> tag
            currentProduct = null;
        } 
        
    }
 
    // "characters" are the text inbetween tags
    public void characters(char[] ch, int start, int length) throws SAXException {
    
        System.out.println("Hello");
        if (currentTag =="id") {
            // don't forget to trim excess spaces from the ends of the string
        String stid = new String(ch, start, length).trim();
         currentProduct.setId(Integer.parseInt(stid));
        } else if(currentTag =="nom"){
            String stnom = new String(ch, start, length).trim();
            currentProduct.setNom(stnom);
       }
        else if(currentTag =="ref"){
            String stref = new String(ch, start, length).trim();
            currentProduct.setReference(stref);
       }
        else if(currentTag =="quantite"){
            String stqt = new String(ch, start, length).trim();
            currentProduct.setQuantite(Integer.parseInt(stqt));
       }
        else if(currentTag =="prix"){
            String stprix = new String(ch, start, length).trim();
            currentProduct.setPrix(Float.parseFloat(stprix));
       }
        
    }
    
}