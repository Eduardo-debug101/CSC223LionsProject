package lionsCheckpointE;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.event.*;

public class Title extends JPanel {
	public JLabel jcomp1;
    public JLabel jcomp2;
    public JLabel jcomp3;
    public JLabel jcomp4;
    public JTextField minArrival;
    public JTextField maxArrival;
    public JTextField minService;
    public JTextField maxService;
    public static JTextArea tb_Area;
    public JLabel jcomp10;
    public JButton bt_Start;
    public JButton bt_reload;
    public JButton bt_Clear;
    
    private JLabel lb_NumOfCusts;
    private JTextField tf_numCustomers;
    private JLabel lb_selfSlow;
    private JTextField tf_selfSlow;
    private JLabel lb_fullQueues;
    private JTextField tf_numOfFullQueues;
    private JLabel lb_selfQueues;
    private JTextField tf_numOfSelfQueues;
    
    
    public static JScrollPane scroll = new JScrollPane (tb_Area);
    public static JScrollBar sb = scroll.getVerticalScrollBar();
    
    public static String TEXT = "BACON";
    public int nminArrival = -1;
    public int nmaxArrival = -1;
    public int nminService = -1;
    public int nmaxService = -1;
    public int selfSlowTime = -1;
    public int numCustomers = -1;
    public int fullQueuesAmt = -1;
    public int selfLanesAmt = -1;
    public int selfSlow = -1;
    public int selfQueuesAmt = -1;
    public int running = -1;
	
	

    public Title() {
        //construct components
        jcomp1 = new JLabel ("Enter minimum arrival time between customers:");
        jcomp2 = new JLabel ("Enter maximum arrival time between customers:");
        jcomp3 = new JLabel ("Enter minimum service time:");
        jcomp4 = new JLabel ("Enter maximum service time:");
        minArrival = new JTextField (5);
        maxArrival = new JTextField (5);
        minService = new JTextField (5);
        maxService = new JTextField (5);
        tb_Area = new JTextArea (5, 5);
        jcomp10 = new JLabel ("Output:");
        bt_Start = new JButton ("Start");
        bt_reload = new JButton ("Reload");
        bt_Clear = new JButton ("Clear");
        lb_NumOfCusts = new JLabel ("Number of customers: ");
        tf_numCustomers = new JTextField (5);
        lb_selfSlow = new JLabel ("self Slow:");
        tf_selfSlow = new JTextField (5);
        lb_fullQueues = new JLabel ("Full Queues: ");
        tf_numOfFullQueues = new JTextField (5);
        lb_selfQueues = new JLabel ("self Queues: ");
        tf_numOfSelfQueues = new JTextField (5);
        
        
        scroll = new JScrollPane(tb_Area);
        

        //set components properties
        jcomp4.setToolTipText ("maxArrival");

        //adjust size and set layout
        setPreferredSize (new Dimension (764, 513));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (minArrival);
        add (maxArrival);
        add (minService);
        add (maxService);
        //add (tb_Area);
        add (scroll);
        add (jcomp10);
        add (bt_Start);
        add (bt_Clear);
        add (lb_NumOfCusts);
        add (tf_numCustomers);
        add (lb_selfSlow);
        add (tf_selfSlow);
        add (lb_fullQueues);
        add (tf_numOfFullQueues);
        add (lb_selfQueues);
        add (tf_numOfSelfQueues);
        add (bt_reload);

        //set component bounds (only needed by Absolute Positioning)
        /*
        jcomp1.setBounds (115, 30, 460, 25);
        jcomp2.setBounds (115, 60, 490, 25);
        jcomp3.setBounds (115, 90, 475, 25);
        jcomp4.setBounds (115, 120, 470, 25);
        minArrival.setBounds (555, 30, 100, 25);
        maxArrival.setBounds (555, 60, 100, 25);
        minService.setBounds (555, 90, 100, 25);
        maxService.setBounds (555, 120, 100, 25);
        tb_Area.setBounds (30, 235, 685, 240);
        scroll.setBounds (30, 235, 685, 240);
        sb.setValue( sb.getMaximum() );
        jcomp10.setBounds (30, 205, 100, 25);
        */
        
        jcomp1.setBounds (15, 30, 345, 25);
        jcomp2.setBounds (15, 55, 345, 25);
        jcomp3.setBounds (15, 80, 345, 25);
        jcomp4.setBounds (15, 105, 345, 25);
        minArrival.setBounds (360, 30, 100, 25);
        maxArrival.setBounds (360, 55, 100, 25);
        minService.setBounds (360, 80, 100, 25);
        maxService.setBounds (360, 105, 100, 25);
        tb_Area.setBounds (30, 235, 685, 240);
        scroll.setBounds (30, 235, 685, 240);
        jcomp10.setBounds (30, 205, 100, 25);
        
        
        bt_Start.setBounds (555, 165, 100, 25);
        bt_Clear.setBounds (115, 170, 100, 25);
        lb_NumOfCusts.setBounds (15, 130, 345, 25);
        tf_numCustomers.setBounds (360, 130, 100, 25);
        lb_selfSlow.setBounds (510, 30, 110, 25);
        tf_selfSlow.setBounds (620, 30, 100, 25);
        lb_fullQueues.setBounds (510, 60, 110, 25);
        tf_numOfFullQueues.setBounds (620, 60, 100, 25);
        lb_selfQueues.setBounds (510, 95, 110, 25);
        tf_numOfSelfQueues.setBounds (620, 95, 100, 25);
        
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        
        tb_Area.append(TEXT);
        
        minArrival.setText("1");
        maxArrival.setText("10");
        minService.setText("1");
        maxService.setText("10");
        tf_numCustomers.setText("30");
        tf_selfSlow.setText("10");
        tf_numOfFullQueues.setText("5");
        tf_numOfSelfQueues.setText("2");
        
        bt_Start.addActionListener(new ActionListener() {
        	
      	  
      	  @Override
      	  public void actionPerformed(ActionEvent e) {
      		  tb_Area.setText(TEXT);
      		  
      		  try {
      			nminArrival = Integer.valueOf(minArrival.getText());
      			nmaxArrival = Integer.valueOf(maxArrival.getText());
      			nminService = Integer.valueOf(minService.getText());
      			nmaxService = Integer.valueOf(maxService.getText());
        		
      			numCustomers = Integer.valueOf(tf_numCustomers.getText());
      			selfSlow = Integer.valueOf(tf_selfSlow.getText());
      			fullQueuesAmt = Integer.valueOf(tf_numOfFullQueues.getText());
      			selfQueuesAmt = Integer.valueOf(tf_numOfSelfQueues.getText());
      		  } catch (NumberFormatException l) {
  				System.out.println("Wrong input try again.");
  			}
      		  
      		  
      		  if (check()) {
      			  Simulator sim = new Simulator(nminArrival, nmaxArrival, nminService, nmaxService, numCustomers, selfSlow, fullQueuesAmt, selfQueuesAmt);
      			  sim.start();
      			  TEXT = sim.allSeeingEye();
      			  update(TEXT);
      		  }
      	  }

		private boolean check() {
			if (nminArrival >= 0 && nmaxArrival >= 0 && nminService >=0 && nmaxService >= 0 && numCustomers >= 0 && selfSlow >= 0 && fullQueuesAmt >= 0 && selfQueuesAmt >= 0 && running < 0) {
				running = 30;
				return true;
			}
			return false;
		}

		
        });
        
        bt_reload.addActionListener(new ActionListener() {
        	  
        	  @Override
        	  public void actionPerformed(ActionEvent e) {
        		  tb_Area.setText(TEXT);
        	  }
        });
        
        bt_Clear.addActionListener(new ActionListener() {
        	  
        	  @Override
        	  public void actionPerformed(ActionEvent e) {
        		  System.out.println("Clearing textboxs");
        		  minArrival.setText("");
        		  maxArrival.setText("");
        		  minService.setText("");
        		  maxService.setText("");
        		  
        		  //tb_Area.append(TEXT);
        	  }
          });
        
        /*
        scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
            public void adjustmentValueChanged(AdjustmentEvent e) {  
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
            }
        });
        */
       
    }
    
    public static void update(String newText) {
    	TEXT = TEXT + newText;
    	tb_Area.setText(TEXT);
    }
    
    public static void checkUpdate() {
    	TEXT = TEXT;
    	tb_Area.setText(TEXT);
    }

    public static void Title() {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Title());
        frame.pack();
        frame.setVisible (true);
    }
}
