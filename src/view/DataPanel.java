package view;
import java.awt.GridLayout;

import javax.swing.JPanel;

import io.Data;
import view.data.AttributePanel;
import view.data.DataTable;

public class DataPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8716864006670971677L;

	public DataPanel(String[] attributes) {
		setLayout(new GridLayout(1, 2));
		add(new DataTable(new Data(attributes, 1000)));
		add(new AttributePanel(attributes));
	}
}
