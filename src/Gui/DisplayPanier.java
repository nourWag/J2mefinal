/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author sawssen
 */
public class DisplayPanier extends Form {
StringItem stNom=new StringItem("NOM :", "robe");
//StringItem stRef=new StringItem("Référence :", "fdgl");
StringItem stPrixU=new StringItem("Prix :","120");
String[] tab1={"1","2","3","4","5","6"};
ChoiceGroup chg2=new ChoiceGroup("Quantité", ChoiceGroup.POPUP,tab1, null);
StringItem stNom2=new StringItem("NOM :", "pantalon");
StringItem stRef2=new StringItem("Référence :", "fdgl");
StringItem stPrixU2=new StringItem("Prix :","70");
//String[] tab1={"1","2","3","4","5","6"};
ChoiceGroup chg22=new ChoiceGroup("Quantité", ChoiceGroup.POPUP,tab1, null);
//StringItem st=new StringItem("___________________________",null);
    Display disp;
    public DisplayPanier(String title) {
        super(title);
        //this.disp=Midlet.midletCupide.disp;
        append(stNom);
        //append(stRef);
        append(chg2);
        append(stPrixU);
        append("\n");
        //append(st);
        append(stNom2);
        //append(stRef);
        append(chg22);
        append(stPrixU2);
        append("\n");
        
    }

}
