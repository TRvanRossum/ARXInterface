import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
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
		setLayout(new GridLayout(2, 1));
		String[] atts = d.getAttributes();
		String[][] data = d.getData();
		
		JTable table = new JTable(data, atts){
            /**
			 * 
			 */
			private static final long serialVersionUID = -3803735335044275361L;

			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
		
		table.setRowSelectionAllowed( true );
		table.setColumnSelectionAllowed( true );

		table.setSelectionForeground( Color.white );
		table.setSelectionBackground( Color.red );
		
		table.setEnabled(false);
		
		JScrollPane pane = new JScrollPane(table);
		add(pane);
		add(new JLabel("Data reading coming soon..."));
	}
}
