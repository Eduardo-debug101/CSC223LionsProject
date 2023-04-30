package lionsCheckpointE.GUI.WestSide;

import lionsCheckpointE.CheckoutLane;
import lionsCheckpointE.Customer;
import lionsCheckpointE.GUI.StoreGUI;
import lionsCheckpointE.Simulator;

import javax.swing.*;
import java.awt.*;

//https://stackoverflow.com/questions/5732058/best-swing-layout-for-2-dimensional-grid-of-buttons
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/BoxLayoutDemoProject/src/layout/BoxLayoutDemo.java
public class ButtonsPanel extends JPanel {
    public ButtonsPanel() {
        this.setBackground(new Color(5, 5, 5, 150));
        GridLayout layout = new GridLayout(0, 1);
        this.setLayout(layout);

        JTextField minArrivalField = new JTextField("3");
        minArrivalField.setAlignmentX((Component.CENTER_ALIGNMENT));
        JLabel minArrival = new JLabel("Min Arrival");
        minArrival.setForeground(Color.WHITE);
        this.add(minArrival);
        this.add(minArrivalField);

        JTextField maxArrivalField = new JTextField("5");
        maxArrivalField.setAlignmentX((Component.CENTER_ALIGNMENT));
        JLabel maxArrival = new JLabel("Max Arrival");
        maxArrival.setForeground(Color.WHITE);
        this.add(maxArrival);
        this.add(maxArrivalField);

        JTextField minServiceField = new JTextField("1");
        minServiceField.setAlignmentX((Component.CENTER_ALIGNMENT));
        JLabel minService = new JLabel("Min Service");
        minService.setForeground(Color.WHITE);
        this.add(minService);
        this.add(minServiceField);

        JTextField maxServiceField = new JTextField("8");
        maxServiceField.setAlignmentX((Component.CENTER_ALIGNMENT));
        JLabel maxService = new JLabel("Max Service");
        maxService.setForeground(Color.WHITE);
        this.add(maxService);
        this.add(maxServiceField);

        JTextField numCustomersField = new JTextField("20");
        numCustomersField.setAlignmentX((Component.CENTER_ALIGNMENT));
        JLabel numCustomers = new JLabel("Num Customers");
        numCustomers.setForeground(Color.WHITE);
        this.add(numCustomers);
        this.add(numCustomersField);

        JTextField selfSlowField = new JTextField("1");
        selfSlowField.setAlignmentX((Component.CENTER_ALIGNMENT));
        JLabel selfSlow = new JLabel("Self Slow Amt");
        selfSlow.setForeground(Color.WHITE);
        this.add(selfSlow);
        this.add(selfSlowField);

        JTextField fullQueuesAmtField = new JTextField("4");
        fullQueuesAmtField.setAlignmentX((Component.CENTER_ALIGNMENT));
        JLabel fullQueuesAmt = new JLabel("Full Queues Amt");
        fullQueuesAmt.setForeground(Color.WHITE);
        this.add(fullQueuesAmt);
        this.add(fullQueuesAmtField);

        JTextField selfQueuesAmtField = new JTextField("3");
        selfQueuesAmtField.setAlignmentX((Component.CENTER_ALIGNMENT));
        JLabel selfQueuesAmt = new JLabel("Self Queues Amt");
        selfQueuesAmt.setForeground(Color.WHITE);
        this.add(selfQueuesAmt);
        this.add(selfQueuesAmtField);

        JLabel dashes = new JLabel("_____________________");
        dashes.setForeground(Color.WHITE);
        this.add(dashes);
        JButton startButton = new JButton();
        startButton.addActionListener(e -> {
            this.startSim(Integer.parseInt(minArrivalField.getText()), Integer.parseInt(maxArrivalField.getText()), Integer.parseInt(minServiceField.getText()), Integer.parseInt(maxServiceField.getText()), Integer.parseInt(numCustomersField.getText()), Integer.parseInt(selfSlowField.getText()), Integer.parseInt(fullQueuesAmtField.getText()), Integer.parseInt(selfQueuesAmtField.getText()));
        });
        startButton.setText("Start");
        startButton.setBackground(Color.WHITE);
        startButton.setFocusable(false);
        startButton.setAlignmentX((Component.CENTER_ALIGNMENT));
        startButton.setBorder(BorderFactory.createBevelBorder(2));

        this.add(startButton);


    }

    protected void startSim(int minArrival, int maxArrival, int minService, int maxService, int numCustomers, int selfSlow, int fullQueuesAmt, int selfQueuesAmt) {
        Simulator sim = new Simulator(minArrival, maxArrival, minService, maxService, numCustomers, selfSlow, fullQueuesAmt, selfQueuesAmt);
        sim.start();
    }
}
