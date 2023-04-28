package lionsCheckpointE;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Credits extends JPanel {
  private JLabel jcomp1;
  private JList jcomp2;

  public Credits() {
      //construct preComponents
      String[] jcomp2Items = {"Team lead - Eduardo Argueta-Pineda", "Developer - Damian Rebollar-Reyes", "Developer - Benjamin Wheatley"};

      //construct components
      jcomp1 = new JLabel ("Credits:");
      jcomp2 = new JList (jcomp2Items);

      //adjust size and set layout
      setPreferredSize (new Dimension (528, 246));
      setLayout (null);

      //add components
      add (jcomp1);
      add (jcomp2);

      //set component bounds (only needed by Absolute Positioning)
      jcomp1.setBounds (45, 30, 100, 25);
      jcomp2.setBounds (45, 80, 430, 80);
      
      
  }


  public static void Credits() {
      JFrame frame = new JFrame ("Credits");
      frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
      frame.getContentPane().add (new Credits());
      frame.pack();
      frame.setVisible (true);
  }
  
}

