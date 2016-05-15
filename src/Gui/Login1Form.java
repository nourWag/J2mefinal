package Gui;



import TunisiaMall.Midlet2;
import com.sun.lwuit.Button;
import com.sun.lwuit.Label;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author snoussi
 */
public class Login1Form extends Form implements CommandListener, Runnable {

    Display disp;
    Midlet2 mMidlet;
    String[] tabrole = {"a:0:{}", "a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}"};
    ChoiceGroup role = new ChoiceGroup("roles", ChoiceGroup.POPUP, tabrole, null);
    private Command exit;
    private Command Connection;
    private TextField username;
    private TextField password;
    Command cmdInscription = new Command("Inscription", Command.SCREEN, 0);
    private HttpConnection http;
    private DataInputStream dataStream;
    private int ch;
    private StringBuffer str = new StringBuffer("");
    String message;
    List lst = new List("Projets", List.IMPLICIT);
    Ticker tk = new Ticker("bienvenue en TunisiaMall ");
//    public static Object user;

    public Login1Form() {
        super("Authentification");
        try {
            append(new ImageItem(null, Image.createImage("/mall.jpg"), ImageItem.LAYOUT_CENTER, "image not found"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        exit = new Command("Quitter", Command.EXIT, 1);
        Connection = new Command("Se connecter", Command.SCREEN, 0);

        addCommand(exit);
        addCommand(Connection);
        addCommand(cmdInscription);

        username = new TextField("login", null, 30, TextField.ANY);
        password = new TextField("mot de passe", null, 30, TextField.PASSWORD);
        append(role);
        append(username);
        append(password);
        this.setCommandListener(this);

    }

    public void commandAction(Command c, Displayable d) {
        if (c == exit) {
            Midlet2.INSTANCE.notifyDestroyed();

        } else if (c == Connection) {
            Thread t = new Thread(this);
            t.start();

            Midlet2.INSTANCE.disp.setCurrent(new PersonneList());

        }

        if (c == cmdInscription) {

            Midlet2.INSTANCE.disp.setCurrent(new AjoutForm());

        }

    }

    public void run() {

        try {
            String user = username.getString();
            String pass = password.getString();
            String roles = String.valueOf(role.getString(role.getSelectedIndex())).trim();

            String requete = "http://localhost:8000/PIDEVhamed/mobile/login1.php?username=" + user + "&password=" + pass + "&roles=" + roles;
            http = (HttpConnection) Connector.open(requete);
            dataStream = http.openDataInputStream();
            str.delete(0, str.length());
            while ((ch = dataStream.read()) != -1) {
                str.append((char) ch);
            }

            if (str.toString().equals("true")) {
                if ("a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}".equals(roles)) {
                    Midlet2.INSTANCE.disp.setCurrent(new PersonneList());
                  
                } else {

        com.sun.lwuit.Display.init(Midlet2.INSTANCE);

        try{
            Resources r=Resources.open("/LWUITtheme.res");
            UIManager.getInstance()
                    .setThemeProps(r.getTheme
                    (r.getThemeResourceNames()[0]));
        }catch (Exception e){}
        
         com.sun.lwuit.Form f=new com.sun.lwuit.Form();
        f.setTitle("Tunisia Mall");
        f.setLayout(new BorderLayout());
        f.addComponent("North", 
                new Label("bienvenus"));
         
        


Button combined; 
combined = new Button("Bienvenus a Tunisia Mall ");
combined.setTextPosition(com.sun.lwuit.Component.BOTTOM);

combined.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
             new Acceuilsn(disp);
             }

});



//Button pictureButton = new Button(image);

//Label bottomText = new Label(image);
//bottomText.setText("Tunisia Mall");
//bottomText.setTextPosition(com.sun.lwuit.Component.BOTTOM);
//f.setLayout(new BorderLayout());
 //f.addComponent(BorderLayout.CENTER, bottomText);
//f.addComponent(bottomText);
 //f.setLayout(new BorderLayout());
 //f.addComponent(BorderLayout.CENTER, bottomText);
//f.addComponent(pictureLabel);
//f.setLayout(new BorderLayout());
 //f.addComponent(BorderLayout.CENTER,label);
 //f.addComponent(label);
     //  f.addComponent(l1);
f.setLayout(new BorderLayout());
f.addComponent(BorderLayout.CENTER,combined);
//f.setLayout(new BorderLayout());
//f.addComponent(BorderLayout.CENTER,button);
        f.show();
        // disp.setCurrent(al,lst);
         
         
                }
            } else {
                Alert alt = new Alert("erreur", "Coordonnées Incorrectes", null, AlertType.ERROR);
                Midlet2.INSTANCE.disp.setCurrent(alt, new Login1Form());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setTicker(tk);

    }

}
