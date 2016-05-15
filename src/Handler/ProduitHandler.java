/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

/**
 *
 * @author pc
 */

import Entity.Produit;
import java.util.Date;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ProduitHandler extends DefaultHandler{
    private Vector produits;
    String idTag = "close";
    String designationTag = "close";
    String marqueTag = "close";
     String descriptionTag = "close";
      String referenceTag = "close";
       String prixancienTag = "close";
        String prixdachatTag = "close";
         String prixVenteTag = "close";
          String quantiteTag = "close";
           String noteTag = "close";
            String NbreVTag = "close";
             String disponibleTag = "close";
              String boutique_idTag = "close";
               String couleurTag = "close";
                String sizeTag = "close";
                 String updatedAtTag = "close";
                 String categorieTag = "close";
                 String soldeTag = "close";
                 String imageNameTag = "close";

                 
    public ProduitHandler() {
        produits= new Vector();
    }

    public Produit[] getProduits() {
        Produit[] prs = new Produit[ produits.size()];
        produits.copyInto(prs);
        return prs;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Produit currentProduits;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if (qName.equals("produit")) {
            currentProduits = new Produit();
            //2ème methode pour parser les attributs
             //System.out.println((attributes.getValue("designation")));
             
           currentProduits.setIdS(attributes.getValue("id"));
            currentProduits.setMarque(attributes.getValue("marque"));
               currentProduits.setDesignation(attributes.getValue("designation"));
                 currentProduits.setDescription(attributes.getValue("description"));
          
                 
                 currentProduits.setReference(attributes.getValue("reference"));
           currentProduits.setPrixancienS(attributes.getValue("prixancien"));
           currentProduits.setPrixdachatS(attributes.getValue("prixdachat"));
            currentProduits.setPrixventeS(attributes.getValue("prixVente"));
            currentProduits.setQuantiteS(attributes.getValue("quantite"));
            
           currentProduits.setNoteS(attributes.getValue("note"));
            currentProduits.setNbreVS(attributes.getValue("NbreV"));
            currentProduits.setDisponible(attributes.getValue("disponible"));
            currentProduits.setBoutique_idS(attributes.getValue("boutique_id"));
            currentProduits.setCouleur(attributes.getValue("couleur"));
            currentProduits.setSize(attributes.getValue("size"));
            currentProduits.setDate(attributes.getValue("updatedAt"));
            currentProduits.setCategories(attributes.getValue("categorie"));
            currentProduits.setSolde(attributes.getValue("solde"));
            currentProduits.setImageName(attributes.getValue("imageName"));
          
          
            /****/
               
         
            
        }
        else if (qName.equals("id")) {
           
            idTag = "open";
        } 
        else if (qName.equals("designation")) {
          designationTag = "open";
        }
        else if (qName.equals("marque")) {
            marqueTag = "open";
        }
        else if (qName.equals("description")) {
            descriptionTag = "open";
        }
        else if (qName.equals("reference")) {
            referenceTag = "open";
        }
        else if (qName.equals("prixancien")) {
            prixancienTag = "open";
        }
        else if (qName.equals("prixdachat")) {
            prixdachatTag = "open";
        }
        else if (qName.equals("prixVente")) {
            prixVenteTag = "open";
        }
        else if (qName.equals("quantite")) {
            quantiteTag = "open";
        }
        else if (qName.equals("note")) {
            noteTag = "open";
        }
        else if (qName.equals("NbreV")) {
            NbreVTag = "open";
        }
        else if (qName.equals("NbreV")) {
            NbreVTag = "open";
        }
        else if (qName.equals("disponible")) {
            disponibleTag = "open";
        }
        else if (qName.equals("boutique_id")) {
            boutique_idTag = "open";
        }
        else if (qName.equals("couleur")) {
            couleurTag = "open";
        }
        else if (qName.equals("size")) {
            sizeTag = "open";
        }
        else if (qName.equals("updatedAt")) {
            updatedAtTag = "open";
        }
        else if (qName.equals("categorie")) {
            categorieTag = "open";
        }
        else if (qName.equals("solde")) {
            soldeTag = "open";
        }
        else if (qName.equals("imageName")) {
            imageNameTag = "open";
        }
        
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("produit")) {
            // we are no longer processing a <reg.../> tag
             produits.addElement(currentProduits);
            currentProduits = null;
        }
        else if (qName.equals("id")) {
            idTag = "close";
        } 
        else if (qName.equals("designation")) {
            designationTag = "close";
        }
        else if (qName.equals("marque")) {
            marqueTag = "close";
        }
        else if (qName.equals("description")) {
            descriptionTag = "close";
        }
        else if (qName.equals("reference")) {
            referenceTag = "close";
        }
        else if (qName.equals("prixancien")) {
            prixancienTag = "close";
        }
        else if (qName.equals("prixdachat")) {
            prixdachatTag = "close";
        }
        else if (qName.equals("prixVente")) {
            prixVenteTag = "close";
        }
        else if (qName.equals("quantite")) {
            quantiteTag = "close";
        }
        else if (qName.equals("note")) {
            noteTag = "close";
        }
        else if (qName.equals("NbreV")) {
            NbreVTag = "close";
        }
        
        else if (qName.equals("disponible")) {
            disponibleTag = "close";
        }
        else if (qName.equals("boutique_id")) {
            boutique_idTag = "close";
        }
        else if (qName.equals("couleur")) {
            couleurTag = "close";
        }
        else if (qName.equals("size")) {
            sizeTag = "close";
        }
        else if (qName.equals("updatedAt")) {
            updatedAtTag = "close";
        }
        else if (qName.equals("categorie")) {
            categorieTag = "close";
        }
        else if (qName.equals("solde")) {
            soldeTag = "close";
        }
        else if (qName.equals("imageName")) {
            imageNameTag = "close";
        }
        
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentProduits!= null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
               // System.out.println(id);
                currentProduits.setId(Integer.parseInt(id));
                //System.out.println(currentProduits.getID());
            } else
                if (designationTag.equals("open")) {
                String designation= new String(ch, start, length).trim();
              currentProduits.setDesignation(designation);
            } else
                    if (marqueTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProduits.setMarque(pren);
            } else
                    if (descriptionTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProduits.setDescription(pren);
            } else
                    if (referenceTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProduits.setReference(pren);
            } else
                    if (prixancienTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                //System.out.println(pren);
                currentProduits.setPrixancien(Float.parseFloat(pren));
            }
            else
                    if (prixdachatTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProduits.setPrixdachat(Float.parseFloat(pren));
            }
            else
                    if (prixVenteTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProduits.setPrixvente(Float.parseFloat(pren));
            }
            else
                    if (quantiteTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProduits.setQuantite(Integer.parseInt(pren));
            }
            else
                    if (noteTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProduits.setNote(Integer.parseInt(pren));
            }
            else
                    if (NbreVTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProduits.setNbreV(Integer.parseInt(pren));
            }
            else
                    if (disponibleTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProduits.setDisponible(pren);
            }
            else
                    if (boutique_idTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProduits.setBoutique_id(Integer.parseInt(pren));
            }
            else
                    if (couleurTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProduits.setCouleur(pren);
            }
            else
                    if (sizeTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProduits.setSize(pren);
            }
            else
                    if (updatedAtTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
               
                currentProduits.setDate(pren);
            }
            else
                    if (categorieTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
               
                currentProduits.setCategories(pren);
            }
            else
                    if (soldeTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
               
                currentProduits.setSolde(pren);
            }
            else
                    if (imageNameTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
               
                currentProduits.setImageName(pren);
            }
            
        }
    }
    
}

