package Gui;
import TunisiaMall.Midlet2;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;

/**
 *
 * @author pc
 */
public class AjoutBoutique implements CommandListener, ItemStateListener {

    Display disp;
    //Form 1
    Form f1 = new Form("Ajouter Boutique");
    Command cmBck = new Command("retour", Command.SCREEN, 0);
    private int defaultIndex = 0;
    TextField tfid = new TextField("Id", null, 100, TextField.ANY);
    TextField tfnom = new TextField("nom", null, 100, TextField.ANY);
    TextField tfcategorie = new TextField("categorie", null, 100, TextField.ANY);
    TextField tfFax = new TextField("Fax", null, 100, TextField.ANY);
    TextField tfNum = new TextField("numero Telephone", null, 100, TextField.NUMERIC);
    TextField tfEmail = new TextField("Email", null, 100, TextField.ANY);
    TextField tfpromotion = new TextField("promotion", null, 100, TextField.ANY);
    TextField tfDescription = new TextField("Description", null, 100, TextField.ANY);

    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdBack = new Command("Retour", Command.BACK, 0);

    Form f2 = new Form("Boutique ajouter avec succée");
    Form f3 = new Form("Erreur");

    Alert alerta = new Alert("Erreur", "Desole", null, AlertType.ERROR);

    //Connexion
    HttpConnection hc;
    DataInputStream dis;

    String url = "http://localhost/parsingSalma/insertB.php";
    StringBuffer sb = new StringBuffer();
    int ch;

    public AjoutBoutique(Display disp) {
        this.disp = disp;
        this.startApp();
    }

    public void startApp() {

        f1.append(tfid);
        f1.append(tfnom);
        f1.append(tfcategorie);
        f1.append(tfEmail);
        f1.append(tfDescription);
        f1.append(tfFax);
        f1.append(tfNum);
        f1.append(tfpromotion);
        
        f1.addCommand(cmdValider);
        f1.addCommand(cmBck);
        f1.setCommandListener(this);
        f2.addCommand(cmdBack);
        f2.setCommandListener(this);
        f1.setItemStateListener(this);
        disp.setCurrent(f1);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdValider) {
           new AffichageBoutique("boutique",Midlet2.INSTANCE.disp);
           
        }
        if (c == cmdBack) {

            disp.setCurrent(f1);
        }
        if (c == cmBck) {

        }
    }

    public void ajoutBoutique() {
        try {
            hc = (HttpConnection) Connector.open(url + "?id=" + tfid.getString().trim() + "&nom=" + tfnom.getString().trim() + "&categorie=" + tfcategorie.getString().trim() + "&Fax=" + tfFax.getString().trim() + "&numero_telephone=" + tfNum.getString().trim() + "&Email=" + tfEmail.getString().trim() + "&promotion=" + tfpromotion.getString().trim() + "&Description=" + tfDescription.getString().trim());
            //System.out.println(url+"?id="+tfid.getString().trim()+"&designation="+ tfdes.getString().trim()+"&marque="+tfmarque.getString().trim()+"&date="+Integer.toString(day).trim()+"-"+Integer.toString(month).trim()+"-" + Integer.toString(year).trim());  
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equals(sb.toString().trim())) {
                disp.setCurrent(f2);
            } else {
                disp.setCurrent(f3);
            }
            sb = new StringBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void itemStateChanged(Item item) {

    }
}

