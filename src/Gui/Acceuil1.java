package Gui;
import TunisiaMall.Midlet2;
import com.sun.lwuit.Button;
import com.sun.lwuit.Component;
import com.sun.lwuit.Display;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author pc
 */
public class Acceuil1 implements CommandListener, ActionListener {

    StringItem st = new StringItem("message : ", null);
    TextField txt = new TextField("jour", null, 50, TextField.ANY);
    String[] choix = new String[]{"imsek", "iftar"};
    ChoiceGroup chG = new ChoiceGroup("compétence", ChoiceGroup.POPUP, choix, null);

    Image img;
    HttpConnection http;
    DataInputStream data;
    Command cmd = new Command("connexion", Command.OK, 0);

    javax.microedition.lcdui.Display disp;
    Displayable displ;

    public Acceuil1(javax.microedition.lcdui.Display disp) {

        this.disp = disp;
        Display.init(this);

        try {
            Resources r = Resources.open("/LWUITtheme.res");
            UIManager.getInstance()
                    .setThemeProps(r.getTheme(r.getThemeResourceNames()[0]));
        } catch (Exception e) {
        }

        com.sun.lwuit.Form mainForm = new com.sun.lwuit.Form("Acceuil");
        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        final Button button1 = new Button("Tunisia Mall");
        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                button1.setText("Bienvenue");
            }
        });

        mainForm.addComponent(button1);
        try {
            com.sun.lwuit.Image icon1 = com.sun.lwuit.Image.createImage("/icons/8.png");
            Button button2 = new Button(icon1);
            button2.setText("Lister Boutiques");
            button2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ae) {
                    new AffichageBoutique("boutique",Midlet2.INSTANCE.disp);
                }

            });
            mainForm.addComponent(button2);
            com.sun.lwuit.Image icon2 = com.sun.lwuit.Image.createImage("/icons/19.png");
            Button button3 = new Button(icon2);
            button3.setText("ajouter Boutique");

            button3.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ae) {
                    new AjoutBoutique(Midlet2.INSTANCE.disp);
                }

            });
            mainForm.addComponent(button3);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        mainForm.setTransitionOutAnimator(CommonTransitions.createFade(400));
        com.sun.lwuit.Command cmdBack = new com.sun.lwuit.Command("Back", 2);

        mainForm.addCommand(cmdBack);
        mainForm.setCommandListener(this);
        mainForm.show();

    }
    public void connexion() {

    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmd) {
        }

    }
    public void actionPerformed(ActionEvent ae) {
        new Acceuilsn(Midlet2.INSTANCE.disp);
    }
}
