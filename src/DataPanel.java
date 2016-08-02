import java.awt.GridLayout;

import javax.swing.JPanel;

import io.Data;

public class DataPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8716864006670971677L;

	public DataPanel(String[] attributes) {
		setLayout(new GridLayout(1, 2));
		add(new DataTable(new Data(attributes, 100)));
		add(new AttributePanel(attributes));
	}
}
