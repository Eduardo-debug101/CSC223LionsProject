package lionsCheckpointE.GUI;

import lionsCheckpointE.GUI.CenterPanel.CenterConsolePanel;
import lionsCheckpointE.GUI.EastSide.PlaceholderPanel;
import lionsCheckpointE.GUI.NorthSide.BannerPanel;
import lionsCheckpointE.GUI.SouthSide.BottomPanel;
import lionsCheckpointE.GUI.WestSide.WestControlsPanel;

import javax.swing.*;
import java.awt.*;

// Credits:
// Bro Code - https://www.youtube.com/watch?v=Kmgo00avvEw - Java Swing tutorial
// Java2s.com - http://www.java2s.com/Tutorials/Java/Swing_How_to/JFrame/Create_Console_JFrame.htm - System.out to a text area of the GUI

public class StoreGUI extends JFrame {

    BackgroundPanel2 imagePanel = new BackgroundPanel2(new ImageIcon(getClass().getResource("futuretextbox.jpg")).getImage());
    CenterConsolePanel consolePanel = new CenterConsolePanel(this.getWidth(), this.getHeight());

    public StoreGUI() {

        // Border layout - https://www.youtube.com/watch?v=PD6pd6AMoOI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Lion's Java Market");

        this.setVisible(true);
        this.setLayout(new BorderLayout());

        this.setContentPane(imagePanel);
        this.add(new BannerPanel(), BorderLayout.NORTH);
        this.add(new WestControlsPanel(), BorderLayout.WEST);
        this.add(consolePanel, BorderLayout.CENTER);
        this.add(new PlaceholderPanel(), BorderLayout.EAST);
        this.add(new BottomPanel(), BorderLayout.SOUTH);

//        this.add(consolePanel);

        //this.setLocationRelativeTo(null);
        this.pack();

    }

    protected void redrawConsole() {
        this.consolePanel = new CenterConsolePanel(this.getWidth(), this.getHeight());
    }
}
