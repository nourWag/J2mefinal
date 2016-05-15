/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;

import TunisiaMall.Midlet2;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

/**
 *
 * @author nour
 */
public class musicForm extends Form implements CommandListener, Runnable{

    Command cmdNext1 = new Command("boutique", Command.SCREEN, 0);
    Command cmdNext2 = new Command("produit", Command.EXIT, 0);
    
    public musicForm(String title) {
        super(title);
        addCommand(cmdNext1);
        addCommand(cmdNext2);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
if (c == cmdNext1) {
            Display disp = null;
            Midlet2.INSTANCE.disp.setCurrent(new AffichageBoutique("boutique", disp));
        }
        if (c == cmdNext2) {
            Display disp = null;
            Midlet2.INSTANCE.disp.setCurrent(new PersonneList());
        }
        
    }

    public void run() {

    }

}
