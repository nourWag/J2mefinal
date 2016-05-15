/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author ND
 */
class PieChartCanvas extends Canvas {

    int[] data;

    int colors[] = {0xFF0000, 0xA9E969, 0x00FFFF, 0xC675EC, 0x008800, 0x00C400};

    public PieChartCanvas(int[] data) {
        this.data = data;
    }

    public void paint(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();

        g.setColor(255, 255, 255);
        g.fillRect(0, 0, width, height);

        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        int deltaAngle = 360 * 100 / sum / 100;
        int x = 4;
        int y = 4;
        int diameter;

        if (width > height) {
            diameter = height - y * 2;
        } else {
            diameter = width - x * 2;
        }

        int startAngle = 0;

        for (int i = 0; i < data.length; i++) {
            g.setColor(colors[i]);
            g.fillArc(x, y, diameter, diameter, startAngle, deltaAngle * data[i]);
            startAngle += deltaAngle * data[i];
        }
    }
}
