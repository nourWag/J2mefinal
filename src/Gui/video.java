/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import TunisiaMall.Midlet2;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.GUIControl;
import javax.microedition.media.control.VideoControl;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author nour
 */
public class video implements CommandListener{
  Display disp ;  

  private List list = new List("Pick One", List.IMPLICIT);

  private Canvas canvas = new VideoCanvas();

  private Form form = new Form("Video Form", null);

  private StringItem descriptionItem = new StringItem("Desc: ", "Bad audio");

  Player player = null;

  private Command backCommand = new Command("Back", Command.ITEM, 1);

  private Command exitCommand = new Command("Exit", Command.EXIT, 1);

  private Alert alert = new Alert("Error");

  //private Display display = null;

  private boolean error = false;

  public video(Display disp) {
    this.disp=disp;

    canvas.addCommand(exitCommand);
    canvas.addCommand(backCommand);
    canvas.setCommandListener(this);

    form.append(descriptionItem);
    form.addCommand(exitCommand);
    form.addCommand(backCommand);
    form.setCommandListener(this);

    list.append("Play Video on Form", null);
    list.append("Play Video on Canvas", null);
    list.addCommand(exitCommand);
    list.setCommandListener(this);
     this.startApp();
  }

  public void startApp() {
    if (error)
      return;
    disp.setCurrent(list);
  }

  public void pauseApp() {
  }

  public void destroyApp(boolean unconditional) {
    try {
      if (player != null)
        player.close();
    } catch (Exception e) {
      error(e);
    }
  }

  public void commandAction(Command cmd, Displayable disp1) {
    if (cmd == exitCommand) {
     
      
    } else if (cmd == backCommand) {
      try {
        if (player != null)
          player.close();
      } catch (Exception e) {
        error(e);
      }
      Midlet2.INSTANCE.disp.setCurrent(list);
      return;
    }
    try {
      loadPlayer();
      if (list.getSelectedIndex() == 0) {
        GUIControl guiControl = (GUIControl) player .getControl("javax.microedition.media.control.GUIControl");

        if (guiControl == null)
          throw new Exception("No GUIControl!!");

        Item videoItem = (Item) guiControl.initDisplayMode(GUIControl.USE_GUI_PRIMITIVE, null);

        form.insert(0, videoItem);

        disp.setCurrent(form);

        player.start();
      } else {
        VideoControl videoControl = (VideoControl) player.getControl("javax.microedition.media.control.VideoControl");
        if (videoControl == null)
          throw new Exception("No VideoControl!!");
        videoControl.initDisplayMode(VideoControl.USE_DIRECT_VIDEO, canvas);
        videoControl.setDisplayFullScreen(true);
        videoControl.setVisible(true);
        Midlet2.INSTANCE.disp.setCurrent(canvas);
        player.start();
      }
    } catch (Exception e) {
      error(e);
    }
  }

  private void loadPlayer() throws Exception {
    player = Manager.createPlayer(getClass().getResourceAsStream("/pi/r.mpg"), "video/mpeg");
    player.realize();
  }

  private void error(Exception e) {
    alert.setString(e.getMessage());
    alert.setTimeout(Alert.FOREVER);
    disp.setCurrent(alert);
    e.printStackTrace();
    error = true;
  }
}

class VideoCanvas extends Canvas {
  public void paint(Graphics g) {
  }
}