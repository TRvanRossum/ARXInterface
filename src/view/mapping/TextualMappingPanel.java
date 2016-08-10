package view.mapping;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.data.config.Configuration;

public class TextualMappingPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1408545498602896683L;
	private Configuration config;
	private JTable table;
	private JScrollPane scrollPane;
	
	public TextualMappingPanel(Configuration cfg) {
		super(new GridLayout(2, 1));
		config = cfg;
		createInterface(config);
	}
	
	private void createInterface(Configuration c) {
		table = new JTable(new String[0][0], new String[]{"Attribute name", "Attribute values (separate by comma)", "Maps to value"});
		 
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
		add(scrollPane);
		        
	}
	
	public void updateInterface(Configuration c){
		remove(scrollPane);
		createInterface(c);
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
