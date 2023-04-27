package lionsCheckpointE.GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	
	Image image;

	BackgroundPanel() {
		image = new ImageIcon(getClass().getResource("future.jpg")).getImage();
		this.setPreferredSize(new Dimension(1200, 720));
	}

	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g);
		g2D.drawImage(image, 0, 0, null);
	}
}


