/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;



/**
 *
 * @author snoussi
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author snoussi
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.microedition.lcdui.*;
//import static javax.microedition.lcdui.Canvas.DOWN;
//import static javax.microedition.lcdui.Canvas.LEFT;
//import static javax.microedition.lcdui.Canvas.RIGHT;
//import static javax.microedition.lcdui.Canvas.UP;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;

/**
 *
 * @author TOSHIBA        
 */
public class Canvasmenu extends Canvas implements CommandListener {

    public String imageTile = "/menu1.png";
    private int i = 1;
    public int currentTileNumber;
    public Command selectCommand = new Command("Sélectionner", Command.SCREEN, 0);
    public Command exit = new Command("Exit", Command.EXIT, 0);
    public Canvasmenu Canvasmenu;
    private Player player;

    public Canvasmenu() {
        this.setCommandListener(this);
        this.addCommand(exit);
        this.addCommand(selectCommand);
        this.setFullScreenMode(true);
    }

    private String myActionMsg = "";

    protected void moveImage(int keyCode) {
        int key = -1;
        try {
            key = getGameAction(keyCode);
        } catch (Exception ex) {
            key = keyCode;
        }

        switch (key) {

            case UP:
                if (i > 1 && i <= 4) {
                    i -= 2;
                    imageTile = "/menu" + i + ".png";
                    Canvasmenu = new Canvasmenu();
                    currentTileNumber = i;
//                    cleanUp();
//                    playMedia("/dossier.wav");
                    //System.out.println("UP" + imageTile);

                }
                break;

            case DOWN:
                if (i > 0 && i <= 2) {
                    i += 2;
                    imageTile = "/menu" + i + ".png";
                    Canvasmenu = new Canvasmenu();
                    currentTileNumber = i;
//                    cleanUp();
//                    playMedia("/1.wav");

                }
                break;

            case RIGHT:
                if (i >= 1 && i < 4) {
                    i += 1;
                    imageTile = "/menu" + i + ".png";
                    Canvasmenu = new Canvasmenu();
                    currentTileNumber = i;
//                    cleanUp();
//                    playMedia("/1.wav");
                }
                break;

            case LEFT:
                if (i > 1 && i <= 4) {
                    i -= 1;
                    //System.out.println("UP " + i);
                    imageTile = "/menu" + i + ".png";
                    Canvasmenu = new Canvasmenu();
                    currentTileNumber = i;
//                    cleanUp();
//                    playMedia("/1.wav");
                }
                break;
        }
    }

    private Image resizedImage(Image src) {
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        Image tmp = Image.createImage(getWidth(), srcHeight);
        Graphics g = tmp.getGraphics();
        int ratio = (srcWidth << 16) / getWidth();
        int pos = ratio / 2;

        //Horizontal Resize        
        for (int x = 0; x < getWidth(); x++) {
            g.setClip(x, 0, 1, srcHeight);
            g.drawImage(src, x - (pos >> 16), 0, Graphics.LEFT | Graphics.TOP);
            pos += ratio;
        }

        Image resizedImage = Image.createImage(getWidth(), getHeight());
        g = resizedImage.getGraphics();
        ratio = (srcHeight << 16) / getHeight();
        pos = ratio / 2;

        //Vertical resize
        for (int y = 0; y < getHeight(); y++) {
            g.setClip(0, y, getWidth(), 1);
            g.drawImage(tmp, 0, y - (pos >> 16), Graphics.LEFT | Graphics.TOP);
            pos += ratio;
        }
        return resizedImage;

    }

    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        try {
            Image image = Image.createImage(imageTile);
            image = resizedImage(image);
            System.out.println(currentTileNumber);
            g.drawImage(image, 0, 0, Graphics.TOP | Graphics.LEFT);
        } catch (IOException ex) {
            g.setColor(0xffffff);
            g.drawString("Failed to load image!", 0, 0, Graphics.TOP | Graphics.LEFT);
            return;
        }

    }

    public void keyPressed(int keyCode) {
        moveImage(keyCode);
        repaint();
    }

    public void keyRepeated(int keyCode) {
        moveImage(keyCode);
        repaint();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == selectCommand) {
            if (currentTileNumber == 1) {
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//                DossierMedicalForm dossierMedicalForm = new DossierMedicalForm("Dossier médical", List.IMPLICIT);
               // dossierMedicalForm.showDossierMedical();
//                cleanUp();
               // MainMidlet.d.setCurrent(dossierMedicalForm);
            }
        }
        if (c == selectCommand) {
            if (currentTileNumber == 2) {
//                System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
//                cleanUp();
            }
        }
        if (c == selectCommand) {
            if (currentTileNumber == 3) {
//                cleanUp();
          //      MainMidlet.d.setCurrent(new GoogleMapsZoomCanvas(null, d));
            }
        }
        if (c == selectCommand) {
            if (currentTileNumber == 4) {
                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
            //    SpecialityForm x = new SpecialityForm("Dossier médical", List.IMPLICIT);
//                x.showSpeciality();
               // cleanUp();
             //   MainMidlet.d.setCurrent(x);
            }
        }

    }

//    public void playMedia(String lactor) {
//        try {
//            player = Manager.createPlayer(getClass().getResourceAsStream("/dossier.wav"), "audio/x-wav");
//            player.realize();
//            player.start();
//        } catch (Exception e) {
//        }
//    }
//
//    public void cleanUp() {
//        if (player != null) {
//            player.close();
//        }
//        player = null;
//    }
}
