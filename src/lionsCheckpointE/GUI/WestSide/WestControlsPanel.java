package lionsCheckpointE.GUI.WestSide;

import lionsCheckpointE.Simulator;

import javax.swing.*;
import java.awt.*;

public class WestControlsPanel extends JPanel{

    public WestControlsPanel() {
        this.setBackground(Color.GREEN);
        this.setPreferredSize(new Dimension(250, 100));
        this.setLayout(new BorderLayout());

        ButtonsPanel bp = new ButtonsPanel();
        this.add(bp);

    }

    protected void startSim(int minArrival, int maxArrival, int minService, int maxService, int numCustomers,
                            int selfSlow, int fullQueuesAmt, int selfQueuesAmt) {
        Simulator sim = new Simulator(minArrival, maxArrival, minService, maxService, numCustomers, selfSlow,
                fullQueuesAmt, selfQueuesAmt);
        sim.start();
    }
}
