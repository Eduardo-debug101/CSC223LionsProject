package lionsCheckpointE.GUI.WestSide;

import lionsCheckpointE.Simulator;

import javax.swing.*;
import java.awt.*;

public class WestControlsPanel extends JPanel{

    public WestControlsPanel() {
        this.setPreferredSize(new Dimension(150, 100));
        this.setLayout(new BorderLayout());

        ButtonsPanel bp = new ButtonsPanel();
        this.add(bp);

    }
}
