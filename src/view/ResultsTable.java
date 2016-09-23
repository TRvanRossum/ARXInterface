package view;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import io.Data;

class ResultsTable {
	
	private String[] atts;
	private String[][] data;
	private JTable dataTable;
	private DefaultTableModel tableModel;
	
	ResultsTable(Data d){
		atts = d.getAttributes();
		data = d.getData();
		
		dataTable = new JTable(){
            /**
			 * 
			 */
			private static final long serialVersionUID = -3803735335044275361L;

			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
        
        tableModel = new DefaultTableModel(data, atts);
        dataTable.setModel(tableModel);
		
		dataTable.setRowSelectionAllowed( true );
		dataTable.setColumnSelectionAllowed( true );

		dataTable.setSelectionForeground( Color.white );
		dataTable.setSelectionBackground( Color.red );
		
		dataTable.setEnabled(false);
	}
	
	JTable getDataTable(){ 
		return dataTable;
	}
	
	void update(Data d) {
		tableModel.setDataVector(d.getData(), d.getAttributes());
		tableModel.fireTableDataChanged();
		dataTable.repaint();
	}
}
