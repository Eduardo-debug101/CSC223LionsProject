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

	MyPanel panel;
	protected static JTextArea textArea;

	public StoreGUI() {
		panel = new MyPanel();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Lion's Java Market");
		this.add(panel);
		this.pack();
		this.setLayout(null);
		this.setLocationRelativeTo(null);
	}

	public void display() {
		this.setVisible(true);
		setupControlsSection();
		setupConsoleSection();
	}

	private void setupControlsSection() {
		JPanel controlPanel = new JPanel();
		
		JTextField minArrivalField = new JTextField(5);
		JTextField maxArrivalField = new JTextField(5);
		JTextField minServiceField = new JTextField(5);
		JTextField maxServiceField = new JTextField(5);
		JTextField numCustomersField = new JTextField(5);
		JTextField selfSlowField = new JTextField(5);
		JTextField fullQueuesAmtField = new JTextField(5);
		JTextField selfQueuesAmtField = new JTextField(5);

		Object[] fields = { "minArrival:", minArrivalField, "maxArrival:", maxArrivalField, "minService:",
				minServiceField, "maxService:", maxServiceField, "numCustomers:", numCustomersField, "selfSlow:",
				selfSlowField, "fullQueuesAmt:", fullQueuesAmtField, "selfQueuesAmt:", selfQueuesAmtField };

		controlPanel.setBackground(new Color(54, 54, 56));
		controlPanel.setBounds(0, 0, 250, this.getHeight());

		JLabel controlsIDText = new JLabel("Controls");
		controlsIDText.setForeground(Color.WHITE);
		controlsIDText.setBounds(90, 0, 50, 25);
		controlPanel.add(controlsIDText);

		JButton startButton = new JButton();
		startButton.addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(null, fields, "Enter values for variables:",
					JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				int minArrival = Integer.parseInt(minArrivalField.getText());
				int maxArrival = Integer.parseInt(maxArrivalField.getText());
				int minService = Integer.parseInt(minServiceField.getText());
				int maxService = Integer.parseInt(maxServiceField.getText());
				int numCustomers = Integer.parseInt(numCustomersField.getText());
				int selfSlow = Integer.parseInt(selfSlowField.getText());
				int fullQueuesAmt = Integer.parseInt(fullQueuesAmtField.getText());
				int selfQueuesAmt = Integer.parseInt(selfQueuesAmtField.getText());
				this.startSim(minArrival, maxArrival, minService, maxService, numCustomers, selfSlow, fullQueuesAmt,
						selfQueuesAmt);
			} else {
				JOptionPane.showMessageDialog(null, "Simulation was cancelled.", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}

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

		int zCoordinate = 0;
		this.setComponentZOrder(startButton, zCoordinate);
	}

	private void setupConsoleSection() {
		JPanel consolePanel = new JPanel();
		consolePanel.setBackground(new Color(0, 0, 0, 0));
		consolePanel.setBounds(249, 0, (this.getWidth() - 250), this.getHeight());

		textArea = new MyTextArea(24, 80);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setBackground(new Color(1, 1, 1, (float) 0.01));
		textArea.setForeground(Color.WHITE);

		System.setOut(new PrintStream(new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				textArea.append(String.valueOf((char) b));
				textArea.setCaretPosition(textArea.getDocument().getLength());
			}
		}));

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setOpaque(false); // make the scroll pane transparent
		scroll.setBounds(0, 0, 935, 685);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		MyPanel backgroundPanel = new MyPanel();
		this.add(backgroundPanel);

		consolePanel.add(scroll);
		this.add(consolePanel);

		System.out.println("Lion Group's Java Market: Checkpoint E");
		System.out.println("Created by Eduardo Argueta-Pineda, Damian Rebollar-Reyes, and Benjamin Wheatley");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Ready!");
	}

	private void startSim(int minArrival, int maxArrival, int minService, int maxService, int numCustomers,
			int selfSlow, int fullQueuesAmt, int selfQueuesAmt) {
		Simulator sim = new Simulator(minArrival, maxArrival, minService, maxService, numCustomers, selfSlow,
				fullQueuesAmt, selfQueuesAmt);
		sim.start();
	}
}
