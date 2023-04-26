package lionsCheckpointE;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

// Credits:
// Bro Code - https://www.youtube.com/watch?v=Kmgo00avvEw - Java Swing tutorial
// Java2s.com - http://www.java2s.com/Tutorials/Java/Swing_How_to/JFrame/Create_Console_JFrame.htm - System.out to a text area of the GUI

public class StoreGUI extends JFrame {

    public StoreGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Lion's Java Market");
        this.setSize(1200, 720);
        this.setLayout(null);

    }

    public void display() {
        this.setVisible(true);
        setupControlsSection();
        setupConsoleSection();
    }

    private void setupControlsSection() {

        int minArrival = 1;
        int maxArrival = 5;
        int minService = 5;
        int maxService = 15;
        int numCustomers = 20;
        int selfSlow = 0;
        int fullQueuesAmt = 3;
        int selfQueuesAmt = 4;

        JPanel controlPanel = new JPanel();

        controlPanel.setBackground(new Color(54, 54, 56));
        controlPanel.setBounds(0, 0, 250, this.getHeight());

        JLabel controlsIDText = new JLabel("Controls");
        controlsIDText.setForeground(Color.WHITE);
        controlsIDText.setBounds(90, 0, 50, 25);
        controlPanel.add(controlsIDText);

        JButton startButton = new JButton();
        startButton.addActionListener(e -> {
            this.startSim(minArrival, maxArrival, minService, maxService, numCustomers, selfSlow, fullQueuesAmt, selfQueuesAmt);
            // Displays the menu again to clear the console
            this.setupConsoleSection();
        });
        startButton.setText("Start");
        startButton.setBounds(55, 500, 125, 40);
        startButton.setBackground(Color.WHITE);
        startButton.setFocusable(false);
        startButton.setBorder(BorderFactory.createBevelBorder(1));

        this.add(startButton);

        this.add(controlPanel);
    }

    private void setupConsoleSection() {
        JPanel consolePanel = new JPanel();
        consolePanel.setBackground(Color.BLACK);
        consolePanel.setBounds(249, 0, (this.getWidth() - 250), this.getHeight());

        JTextArea output = new JTextArea(24, 80);
        output.setEditable(false);
        output.setBackground(new Color(34, 32, 36));
        output.setForeground(Color.WHITE);

        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                output.append(String.valueOf((char) b));
                output.setCaretPosition(output.getDocument().getLength());
            }
        }));

        JScrollPane scroll = new JScrollPane(output);
        scroll.setBounds(0, 0, 935, 685);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        consolePanel.add(scroll);
        this.add(consolePanel);

        System.out.println("Lion Group's Java Market: Checkpoint E");
        System.out.println("Created by Eduardo Argueta-Pineda, Damian Rebollar-Reyes, and Benjamin Wheatley");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Ready!");

    }

    private void startSim(int minArrival, int maxArrival, int minService, int maxService, int numCustomers, int selfSlow, int fullQueuesAmt, int selfQueuesAmt) {
        Simulator sim = new Simulator(minArrival, maxArrival, minService, maxService, numCustomers, selfSlow, fullQueuesAmt, selfQueuesAmt);
        sim.start();
    }
}
