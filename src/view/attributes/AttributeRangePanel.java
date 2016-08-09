package view.attributes;

import javax.swing.JPanel;

import view.data.config.Configuration;

public class AttributeRangePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7046226825638301415L;
	private Configuration cfg;
	
	public AttributeRangePanel(Configuration config) {
		cfg = config;
		create(cfg);
	}
	
	private void create(Configuration c) {
		
	}
	
}
