
package Handler;
import Entity.Boutique;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoutiqueHandler extends DefaultHandler {

    private Vector boutiques;
    String idTag = "close";
    String nomTag = "close";
    String categorieTag = "close";
    String FaxTag = "close";
    String numero_telephoneTag = "close";
    String EmailTag = "close";
    String promotionTag = "close";
    String descriptionTag = "close";

    public BoutiqueHandler() {
        boutiques = new Vector();
    }

    public Boutique[] getBoutiques() {
        Boutique[] prs = new Boutique[boutiques.size()];
        boutiques.copyInto(prs);
        return prs;
    }
    private Boutique currentBoutiques;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("boutique")) {
            currentBoutiques = new Boutique();
            currentBoutiques.setIdS(attributes.getValue("id"));
            currentBoutiques.setNom(attributes.getValue("nom"));
            currentBoutiques.setCategorie(attributes.getValue("Categorie"));
            currentBoutiques.setFax(attributes.getValue("Fax"));
            currentBoutiques.setEmail(attributes.getValue("Email"));
            currentBoutiques.setNumero_telephone(attributes.getValue("numero_telephone"));
            currentBoutiques.setPromotion(attributes.getValue("promotion"));
           currentBoutiques.setDescription(attributes.getValue("Description"));
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("categorie")) {
            categorieTag = "open";
        } else if (qName.equals("Fax")) {
            FaxTag = "open";
        } else if (qName.equals("numero_telephone")) {
            numero_telephoneTag = "open";
        } else if (qName.equals("Email")) {
            EmailTag = "open";
        } else if (qName.equals("promotion")) {
            promotionTag = "open";
        }
        else if (qName.equals("Description")) {
            descriptionTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("boutique")) {
            boutiques.addElement(currentBoutiques);
            currentBoutiques = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("categorie")) {
            categorieTag = "close";
        } else if (qName.equals("Fax")) {
            FaxTag = "close";
        } else if (qName.equals("numero_telephone")) {
            numero_telephoneTag = "close";
        } else if (qName.equals("Email")) {
            EmailTag = "close";
        } else if (qName.equals("promotion")) {
            promotionTag = "close";
        }
        else if (qName.equals("Description")) {
            descriptionTag = "close";
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentBoutiques != null) {
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentBoutiques.setId(Integer.parseInt(id));
            } else if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentBoutiques.setNom(nom);
                
            } else if (categorieTag.equals("open")) {
                String categorie = new String(ch, start, length).trim();
                currentBoutiques.setCategorie(categorie);

            } else if (FaxTag.equals("open")) {
                String Fax = new String(ch, start, length).trim();
                currentBoutiques.setFax(Fax);
            }
            } else if (numero_telephoneTag.equals("open")) {
            String numero_telephone = new String(ch, start, length).trim();
            currentBoutiques.setNumero_telephone(numero_telephone);
            } else if (EmailTag.equals("open")) {
            String Email = new String(ch, start, length).trim();
            currentBoutiques.setEmail(Email);
             } else if (promotionTag.equals("open")) {
            String promotion = new String(ch, start, length).trim();
            currentBoutiques.setPromotion(promotion);
            }
             
            else if (descriptionTag.equals("open")) {
            String Description = new String(ch, start, length).trim();
            currentBoutiques.setDescription(Description);
        }
    }
}
