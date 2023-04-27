package lionsCheckpointE.GUI.WestSide;

import javax.swing.*;
import java.awt.*;

//https://stackoverflow.com/questions/5732058/best-swing-layout-for-2-dimensional-grid-of-buttons
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/BoxLayoutDemoProject/src/layout/BoxLayoutDemo.java
public class ButtonsPanel extends JPanel {
    public ButtonsPanel() {
        this.setBackground(new Color(200, 200, 200, 150));
        //BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        GridLayout layout = new GridLayout(0, 1);
        this.setLayout(layout);

        JTextField minArrivalField = new JTextField(1);
        minArrivalField.setAlignmentX((Component.CENTER_ALIGNMENT));
        this.add(new JLabel("minArrival"));
        this.add(minArrivalField);

        JTextField maxArrivalField = new JTextField(1);
        maxArrivalField.setAlignmentX((Component.CENTER_ALIGNMENT));
        this.add(new JLabel("minArrival"));
        this.add(maxArrivalField);

        JTextField minServiceField = new JTextField(1);
        minServiceField.setAlignmentX((Component.CENTER_ALIGNMENT));
        this.add(new JLabel("minService"));
        this.add(minServiceField);

        JTextField maxServiceField = new JTextField(1);
        maxServiceField.setAlignmentX((Component.CENTER_ALIGNMENT));
        this.add(new JLabel("maxService"));
        this.add(maxServiceField);

        JTextField numCustomersField = new JTextField(1);
        numCustomersField.setAlignmentX((Component.CENTER_ALIGNMENT));
        this.add(new JLabel("numCustomers"));
        this.add(numCustomersField);

        JTextField selfSlowField = new JTextField(1);
        selfSlowField.setAlignmentX((Component.CENTER_ALIGNMENT));
        this.add(new JLabel("selfSlow"));
        this.add(selfSlowField);

        JTextField fullQueuesAmtField = new JTextField(1);
        fullQueuesAmtField.setAlignmentX((Component.CENTER_ALIGNMENT));
        this.add(new JLabel("fullQueuesAmt"));
        this.add(fullQueuesAmtField);

        JTextField selfQueuesAmtField = new JTextField(1);
        selfQueuesAmtField.setAlignmentX((Component.CENTER_ALIGNMENT));
        this.add(new JLabel("selfQueuesAmt"));
        this.add(selfQueuesAmtField);

        //        Object[] fields = {"minArrival:", minArrivalField, "maxArrival:", maxArrivalField, "minService:",
//                minServiceField, "maxService:", maxServiceField, "numCustomers:", numCustomersField, "selfSlow:",
//                selfSlowField, "fullQueuesAmt:", fullQueuesAmtField, "selfQueuesAmt:", selfQueuesAmtField};

        this.add(new JLabel("___________________________________"));
        JButton startButton = new JButton();
        startButton.addActionListener(e -> {
//            int option = JOptionPane.showConfirmDialog(null, fields, "Enter values for variables:",
//                    JOptionPane.OK_CANCEL_OPTION);
//            if (option == JOptionPane.OK_OPTION) {
//                int minArrival = Integer.parseInt(minArrivalField.getText());
//                int maxArrival = Integer.parseInt(maxArrivalField.getText());
//                int minService = Integer.parseInt(minServiceField.getText());
//                int maxService = Integer.parseInt(maxServiceField.getText());
//                int numCustomers = Integer.parseInt(numCustomersField.getText());
//                int selfSlow = Integer.parseInt(selfSlowField.getText());
//                int fullQueuesAmt = Integer.parseInt(fullQueuesAmtField.getText());
//                int selfQueuesAmt = Integer.parseInt(selfQueuesAmtField.getText());
//                this.startSim(minArrival, maxArrival, minService, maxService, numCustomers, selfSlow, fullQueuesAmt,
//                        selfQueuesAmt);
//            } else {
//                JOptionPane.showMessageDialog(null, "Simulation was cancelled.", "Information",
//                        JOptionPane.INFORMATION_MESSAGE);
//            }

            // Displays the menu again to clear the console
            //this.setupConsoleSection();
        });
        startButton.setText("Start");
        startButton.setBackground(Color.WHITE);
        startButton.setFocusable(false);
        startButton.setAlignmentX((Component.CENTER_ALIGNMENT));
        startButton.setBorder(BorderFactory.createBevelBorder(1));

        this.add(startButton);


    }

//    private static void addAButton(String text, Container container) {
//        JButton button = new JButton(text);
//        button.setAlignmentX(Component.CENTER_ALIGNMENT);
//        container.add(button);
//    }
}
