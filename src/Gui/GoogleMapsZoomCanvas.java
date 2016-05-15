package Gui;

import TunisiaMall.Midlet2;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

import com.jappit.midmaps.googlemaps.GoogleMaps;
import com.jappit.midmaps.googlemaps.GoogleMapsCoordinates;
import com.jappit.midmaps.googlemaps.GoogleMapsMarker;
import com.jappit.midmaps.googlemaps.GoogleMapsPath;
import com.jappit.midmaps.googlemaps.GoogleStaticMap;
import com.jappit.midmaps.googlemaps.GoogleStaticMapHandler;

public class GoogleMapsZoomCanvas extends GoogleMapsTestCanvas implements GoogleStaticMapHandler
{
	GoogleMaps gMaps = null;
	GoogleStaticMap map = null;
	
	Command zoomInCommand, zoomOutCommand;
        Command cmd =new Command("Acceuill", Command.OK, 0);
	
	public GoogleMapsZoomCanvas(MIDlet m, Displayable testListScreen)
	{
		super(m, testListScreen);
		
		addCommand(zoomInCommand = new Command("Zoom in", Command.OK, 1));
		addCommand(zoomOutCommand = new Command("Zoom out", Command.OK, 2));
		this.addCommand(cmd);
                this.setCommandListener(this);
		gMaps = new GoogleMaps();
		
		map = gMaps.createMap(getWidth(), getHeight(), GoogleStaticMap.FORMAT_PNG);
		
		map.setHandler(this);
		
		map.setCenter(new GoogleMapsCoordinates(36.899313, 10.188750));
		GoogleMapsMarker redMarker = new GoogleMapsMarker(new GoogleMapsCoordinates(36.899313, 10.188750));
		redMarker.setColor(GoogleStaticMap.COLOR_RED);
		redMarker.setSize(GoogleMapsMarker.SIZE_MID);
		redMarker.setLabel('E');
		
		map.addMarker(redMarker);
                GoogleMapsPath path = new GoogleMapsPath();
		path.addPoint(new GoogleMapsCoordinates(36.899313, 10.188750));
		path.addPoint(new GoogleMapsCoordinates(36.853514, 10.207149));
		path.setColor(GoogleStaticMap.COLOR_RED);
		path.setWeight(10);
		map.addPath(path);
		map.setZoom(15);
		
		map.update();
	}
	
	protected void paint(Graphics g)
	{
		map.draw(g, 0, 0, Graphics.TOP | Graphics.LEFT);
	}
	public void GoogleStaticMapUpdateError(GoogleStaticMap map, int errorCode, String errorMessage)
	{
		showError("map error: " + errorCode + ", " + errorMessage);
	}
	public void GoogleStaticMapUpdated(GoogleStaticMap map)
	{
		repaint();
	}
	protected void keyPressed(int key)
	{
		int gameAction = getGameAction(key);
		
		if(gameAction == Canvas.UP || gameAction == Canvas.RIGHT || gameAction == Canvas.DOWN || gameAction == Canvas.LEFT)
		{
			map.move(gameAction);
		}
	}
	public void commandAction(Command c, Displayable d)
	{
		super.commandAction(c, d);
		if(c==cmd) new Acceuil(Midlet2.INSTANCE.disp);
		if(c == zoomInCommand)
			map.zoomIn();
		else if(c == zoomOutCommand)
			map.zoomOut();
	}
}