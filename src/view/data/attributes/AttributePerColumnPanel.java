package view.data.attributes;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

public class AttributePerColumnPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3066477756764410002L;
	private AttributePerColumnTable table;

	public AttributePerColumnPanel(String[] attributes) {
		table = new AttributePerColumnTable();
		update(attributes);
	}
	
	public void update(String[] attributes) {
		removeAll();
		List<Component> comps = table.getUpdatedGrid(attributes);
		
		setLayout(new GridLayout(attributes.length, 2));
		for(int i = 0; i < comps.size(); i++){
			add(comps.get(i));
		}
		repaint();
	}
}
