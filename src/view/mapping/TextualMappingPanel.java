package view.mapping;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.data.config.Configuration;

public class TextualMappingPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1408545498602896683L;
	private Configuration config;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton applyButton = new JButton("Apply this mapping");
	
	public TextualMappingPanel(Configuration cfg) {
		super(new GridLayout(2, 1));
		config = cfg;
		createTable(config);
		addHandlers();
	}
	
	private void createTable(Configuration c) {
		DefaultTableModel model = new DefaultTableModel(new String[0][0], new String[]{"Attribute name", "Attribute values (separate by comma)", "Maps to value"});
		table = new JTable(model);
		 
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		 
		if (true) {
		    table.addMouseListener(new MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		            printDebugData(table);
		        }
		    });
		}
		 
		//Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);
		 
		//Add the scroll pane to this panel.
		add(scrollPane, 0);
		        
	}
	
	public void updateTable(Configuration c){
		remove(scrollPane);
		createTable(c);
	}
	
	private void addHandlers() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JButton addRowButton = new JButton("Add a row");
		addRowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.addRow(new String[]{"", "", ""});
			}
			
		});
		panel.add(addRowButton);
		add(panel);
	}
	
	private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();
 
        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
