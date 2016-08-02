import java.awt.GridLayout;

import javax.swing.JPanel;

public class DataPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8716864006670971677L;

	public DataPanel(String[] attributes) {
		setLayout(new GridLayout(1, 2));
		add(new AttributePanel(attributes));
		add(new AttributePanel(attributes));
	}
}
