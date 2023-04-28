package lionsCheckpointE.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class MyTextArea extends JTextArea {

    private Image img;

    public MyTextArea(int a, int b) {
        super(a,b);
        	img = new ImageIcon(getClass().getResource("futuretextbox.jpg")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
    	g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        super.paintComponent(g);
    }
}
