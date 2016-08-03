package view.data;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.data.attributes.AttributePerColumnPanel;

public class AttributePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3537722754246043758L;

	private AttributePerColumnPanel attPanel;
	
	public AttributePanel(String[] attributes){
		setLayout(new GridLayout(2, 1));
		
		attPanel = new AttributePerColumnPanel(attributes);
		
		add(attPanel);
		add(new JButton("Apply"));
	}
}
