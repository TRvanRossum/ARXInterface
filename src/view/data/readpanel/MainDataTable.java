package view.data.readpanel;

import java.awt.Color;

import javax.swing.JTable;

import io.Data;

public class MainDataTable {
	
	private String[] atts;
	private String[][] data;
	private JTable dataTable;
	
	public MainDataTable(Data d) {
		update(d);
	}
	
	public void update(Data d) {
		atts = d.getAttributes();
		data = d.getData();
		
		dataTable = new JTable(data, atts){
            /**
			 * 
			 */
			private static final long serialVersionUID = -3803735335044275361L;

			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
		
		dataTable.setRowSelectionAllowed( true );
		dataTable.setColumnSelectionAllowed( true );

		dataTable.setSelectionForeground( Color.white );
		dataTable.setSelectionBackground( Color.red );
		
		dataTable.setEnabled(false);
	}
	
	public JTable getDataTable() {
		return dataTable;
	}
}
