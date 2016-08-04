package view.data.attributes;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AttributePerColumnPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3066477756764410002L;
	private AttributePerColumnTable table;
	private JPanel mainPanel;

	public AttributePerColumnPanel(String[] attributes) {
		table = new AttributePerColumnTable();
		mainPanel = new JPanel();
		update(attributes);
	}
	
	public void update(String[] attributes) {
		removeAll();
		mainPanel = new JPanel();
		List<Component> comps = table.getUpdatedGrid(attributes);
		
		mainPanel.setLayout(new GridLayout(attributes.length, 2));
		for(int i = 0; i < comps.size(); i++){
			mainPanel.add(comps.get(i));
		}
		JScrollPane pane = new JScrollPane(mainPanel);
		add(pane);
		repaint();
		System.out.println("Test");
	}
}
