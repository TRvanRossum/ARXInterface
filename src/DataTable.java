import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import io.Data;

public class DataTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1302255224765149924L;
	
	public DataTable(Data d) {
		String[] atts = d.getAttributes();
		String[][] data = d.getData();
		
		JTable table = new JTable(data, atts);
		
		// TODO does not work to resize the frame, think about this a bit more.
		table.setSize(960, 1080);
		
		table.setRowSelectionAllowed( true );
		table.setColumnSelectionAllowed( true );

		table.setSelectionForeground( Color.white );
		table.setSelectionBackground( Color.red );
		
		table.setEnabled(false);
		
		JScrollPane pane = new JScrollPane(table);
		add(pane);
	}
}
