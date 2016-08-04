package view.data.attributes;

import java.awt.CardLayout;
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
	private AttributePerColumnTable table = new AttributePerColumnTable();
	private JPanel cards = new JPanel(new CardLayout());
	private String key = "KEY";

	public AttributePerColumnPanel(String[] attributes) {
		add(cards);
		update(attributes);
	}
	
	public void update(String[] attributes) {
		key = key + "KEY";
		CardLayout layout = (CardLayout) cards.getLayout();
		cards.removeAll();
		JPanel mainPanel = new JPanel();
		List<Component> comps = table.getUpdatedGrid(attributes);
		
		mainPanel.setLayout(new GridLayout(attributes.length, 2));
		for(int i = 0; i < comps.size(); i++){
			mainPanel.add(comps.get(i));
		}
		JScrollPane pane = new JScrollPane(mainPanel);
		cards.add(pane, key);
		layout.show(cards, key);
	}
}
