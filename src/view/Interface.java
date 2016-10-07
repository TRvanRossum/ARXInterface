package view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import algorithms.Algorithm;
import algorithms.KAnonMinGenAlgorithm;
import dgh.DGH;
import dgh.DGHInput;
import dgh.database.DGHDatabase;
import functions.MapBuildException;
import functions.NumericalMapping;
import functions.TextualMapping;
import io.Data;
import utils.WindowUtils;
import view.data.config.Configuration;
import view.mapping.NumericalMappingPanel;
import view.mapping.TextualMappingPanel;
import view.results.ResultsFrame;

public class Interface implements ItemListener {
    JPanel cards; //a panel that uses CardLayout
    private final String DATA_PANEL = "Data";
	private final String ANONYMIZATION_PANEL = "Anonymization";
	private final String TEXT_MAPPING_PANEL = "Textual mapping";
	private final String NUMBER_MAPPING_PANEL = "Numerical mapping";
	private Data dummyData = new Data(new String[]{"att1", "att2", "att3", "att4", "att5", "att6", "att7", "att8", "att9"}, new String[5][9]);
	private Configuration config = new Configuration(dummyData, null, null);
	private List<TextualMapping> textMaps;
	private List<NumericalMapping> numberMaps;
	private DGHInput input;
	private DGH dgh;
	private JComboBox<String> cb;
     
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        String comboBoxItems[] = { DATA_PANEL, TEXT_MAPPING_PANEL, NUMBER_MAPPING_PANEL, ANONYMIZATION_PANEL };
        cb = new JComboBox<String>(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        
        JButton textMappingApplyButton = new JButton("Apply");
        JButton numberMappingApplyButton = new JButton("Apply");
        JButton algorithmApplyButton = new JButton("Apply");
        
        //Create the "cards".
        DataPanel card1 = new DataPanel(dummyData.getAttributes());
        TextualMappingPanel card2 = new TextualMappingPanel(config, textMappingApplyButton);
        NumericalMappingPanel card3 = new NumericalMappingPanel(config, numberMappingApplyButton);
        AnonymizationPanel card4 = new AnonymizationPanel(algorithmApplyButton);
        
        textMappingApplyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(card2, "Are you sure that you want to continue"
						+ " with these mappings?");
				if(option == JOptionPane.YES_OPTION) {
					try {
						textMaps = card2.createAllTextualMappings();
						cb.setSelectedIndex(2);
						card3.updateTable(config);
					} catch (MapBuildException e1) {
						JOptionPane.showMessageDialog(card2, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
        	
        });
        
        numberMappingApplyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(card3, "Are you sure that you want to continue"
						+ " with these mappings?");
				if(option == JOptionPane.YES_OPTION) {
					try {
						numberMaps = card3.createAllNumericalMappings();
						input = new DGHInput(config, textMaps, numberMaps);
						cb.setSelectedIndex(3);
						dgh = new DGH(input);
						dgh.generate();
					} catch (MapBuildException e1) {
						JOptionPane.showMessageDialog(card3, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
        	
        });
        
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int option = JOptionPane.showConfirmDialog(card1, "Are you sure you want to use the currently selected data\n" 
        				+ "and configuration per attribute for the algorithm?");
        		if(option == JOptionPane.YES_OPTION) {
        			config = card1.createConfig();
            		cb.setSelectedIndex(1);
            		card2.updateTable(config);
        		}
        	}
        });
        card1.add(applyButton);
        
        algorithmApplyButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Algorithm alg = new KAnonMinGenAlgorithm(card4.getK(), input);
        		try{
        			DGHDatabase res = alg.apply(dgh);
        			new ResultsFrame(res);
        		} catch(Exception x) {
        			JOptionPane.showMessageDialog(card4, "Sufficient anonymization was not possible. Please check\n"
        					+ "your mappings and try again.", "Sufficient anonymization not possible", JOptionPane.ERROR_MESSAGE);
        			x.printStackTrace();
        		}
        	}
        });
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, DATA_PANEL);
        cards.add(card2, TEXT_MAPPING_PANEL);
        cards.add(card3, NUMBER_MAPPING_PANEL);
        cards.add(card4, ANONYMIZATION_PANEL);
         
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
        JFrame frame = new JFrame("Interface - Beta v1.2");
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