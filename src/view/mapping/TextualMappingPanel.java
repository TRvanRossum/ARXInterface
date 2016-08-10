package view.mapping;

import javax.swing.JPanel;
import javax.swing.JTable;

import view.data.config.Configuration;

public class TextualMappingPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1408545498602896683L;
	private Configuration config;
	
	public TextualMappingPanel(Configuration cfg) {
		config = cfg;
		createInterface();
	}
	
	private void createInterface() {
		JTable table = new JTable(config.getData().getData(), config.getData().getAttributes());
		
	}
}
