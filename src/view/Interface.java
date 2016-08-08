package view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import utils.WindowUtils;
import view.data.config.Configuration;

public class Interface implements ItemListener {
    JPanel cards; //a panel that uses CardLayout
    private final String DATA_PANEL = "Data";
	private final String ANONYMIZATION_PANEL = "Anonymization";
	private Configuration config = null;
	private JComboBox<String> cb;
     
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        String comboBoxItems[] = { DATA_PANEL, ANONYMIZATION_PANEL };
        cb = new JComboBox<String>(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
         
        //Create the "cards".
        DataPanel card1 = new DataPanel(new String[]{"att1", "att2", "att3", "att1", "att2", "att3", "att1", "att2", "att3"});
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int option = JOptionPane.showConfirmDialog(card1, "Are you sure you want to use the currently selected data\n" 
        				+ "and configuration per attribute for the algorithm?");
        		if(option == JOptionPane.YES_OPTION) {
        			config = card1.createConfig();
            		cb.setSelectedIndex(1);
        		}
        	}
        });
        card1.add(applyButton);
        
        AnonymizationPanel card3 = new AnonymizationPanel();
         
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, DATA_PANEL);
        cards.add(card3, ANONYMIZATION_PANEL);
         
        pane.add(cards, BorderLayout.CENTER);
    }
     
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Interface - Beta v1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Create and set up the content pane.
        Interface demo = new Interface();
        demo.addComponentToPane(frame.getContentPane());
         
        //Display the window.
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
        WindowUtils.centreWindow(frame);
    }
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", false);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}