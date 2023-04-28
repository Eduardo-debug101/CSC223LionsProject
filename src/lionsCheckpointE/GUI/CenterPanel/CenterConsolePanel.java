package lionsCheckpointE.GUI.CenterPanel;

import javax.crypto.CipherInputStream;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class CenterConsolePanel extends JPanel {

    protected static JTextArea textArea;

    public CenterConsolePanel() {
        this.setPreferredSize(new Dimension(150, 100));
        this.setLayout(new BorderLayout());
        //this.setBounds(249, 0, (frameWidth - 250), frameHeight);
        this.setOpaque(false);

        textArea = new JTextArea(24, 80);
        textArea.setEditable(false);
        textArea.setForeground(Color.WHITE);
        textArea.setOpaque(false);

        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.append(String.valueOf((char) b));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        }));

        JScrollPane scroll = new JScrollPane(textArea);
        //scroll.setBounds(0, 0, 935, 685);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        this.add(scroll, BorderLayout.CENTER);

        System.out.println("Lion Group's Java Market: Checkpoint E");
        System.out.println("Created by Eduardo Argueta-Pineda, Damian Rebollar-Reyes, and Benjamin Wheatley");
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Ready!");
    }
}
