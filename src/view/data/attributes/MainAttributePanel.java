package view.data.attributes;

import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JPanel;

import view.data.config.AttributeClass;
import view.data.config.AttributeType;

public class MainAttributePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4471670639205652639L;
	private AttributeTypePerColumnPanel atpcp;
	private AttributePerColumnPanel apcp;
	
	
	public MainAttributePanel(String[] atts) {
		setLayout(new GridLayout(1, 2));
		atpcp = new AttributeTypePerColumnPanel(atts);
		apcp = new AttributePerColumnPanel(atts);
		add(atpcp);
		add(apcp);
	}
	
	public void update(String[] atts) {
		apcp.update(atts);
		atpcp.update(atts);
	}
	
	public Map<String, AttributeClass> getClassification(){
		return apcp.getPartConfig();
	}
	
	public Map<String, AttributeType> getTypes() {
		return atpcp.getPartConfig();
	}
	
}
