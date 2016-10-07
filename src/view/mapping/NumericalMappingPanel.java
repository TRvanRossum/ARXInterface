package view.mapping;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import functions.MapBuildException;
import functions.NumericalMapping;
import functions.NumericalMappingBuilder;
import view.data.config.AttributeClass;
import view.data.config.AttributeType;
import view.data.config.Configuration;

public class NumericalMappingPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2985703800360788999L;
	
	private Configuration config;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField = new JTextField(",");
	private NumericalMappingBuilder mappingBuilder = new NumericalMappingBuilder();
	
	public NumericalMappingPanel(Configuration cfg, JButton apply) {
		super(new GridLayout(2, 1));
		config = cfg;
		createTable(config);
		addHandlers(apply);
	}
	
	private void createTable(Configuration c) {
		DefaultTableModel model = new DefaultTableModel(new String[0][0], new String[]{"Attribute name", "Ranges (separate by - and chosen delimiter)"});
		if(c.getTypes() != null){
			for(String s : c.getTypes().keySet()) {
				if(c.getTypes().get(s).equals(AttributeType.NUMERICAL) && c.getClassification().get(s).equals(AttributeClass.QUASI)) {
					model.addRow(new String[]{s, ""});
				}
			}
		}
		
		table = new JTable(model);
		 
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		 
		if (true) {
		    table.addMouseListener(new MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		            printDebugData();
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
	
	private void addHandlers(JButton applyButton) {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JButton addRowButton = new JButton("Add a row");
		addRowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.addRow(new String[]{"", ""});
			}
			
		});
		JButton removeRowButton = new JButton("Remove the most recently added row");
		removeRowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				int amtRows = tableModel.getRowCount();
				if(amtRows > 0) {
					tableModel.removeRow(amtRows - 1);
				}
			}
			
		});
		panel.add(addRowButton);
		panel.add(removeRowButton);
		JPanel subPanel = new JPanel(new GridLayout(2, 1));
		subPanel.add(new JLabel("Please specify a delimiter in the textbox..."));
		subPanel.add(textField);
		panel.add(subPanel);
		panel.add(applyButton);
		add(panel);
	}
	
	private void printDebugData() {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        TableModel model = table.getModel();
 
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
	
	public List<NumericalMapping> createAllNumericalMappings(String delim) throws MapBuildException {
		TableModel model = table.getModel();
		List<NumericalMapping> res = new ArrayList<NumericalMapping>();
		for(int i = 0; i < table.getRowCount(); i++) {
			String attribute = (String) model.getValueAt(i, 0);
			String values = (String) model.getValueAt(i, 1);
			res.addAll(mappingBuilder.build(attribute, values, delim));
		}
		
		return res;
	}

	public List<NumericalMapping> createAllNumericalMappings() throws MapBuildException {
		return createAllNumericalMappings(textField.getText());
	}

}
