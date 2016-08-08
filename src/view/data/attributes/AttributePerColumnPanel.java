package view.data.attributes;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.data.config.AttributeClass;

public class AttributePerColumnPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3066477756764410002L;
	private AttributePerColumnTable table = new AttributePerColumnTable();
	private JPanel cards = new JPanel(new CardLayout());
	private JPanel mainPanel;
	private String key = "KEY";
	private List<Component> comps;

	public AttributePerColumnPanel(String[] attributes) {
		add(cards);
		update(attributes);
	}
	
	public void update(String[] attributes) {
		//TODO This is really hacky, should be changed.
		key = key + "KEY";
		CardLayout layout = (CardLayout) cards.getLayout();
		mainPanel = new JPanel();
		comps = table.getUpdatedGrid(attributes);
		
		mainPanel.setLayout(new GridLayout(attributes.length, 2));
		for(int i = 0; i < comps.size(); i++){
			mainPanel.add(comps.get(i));
		}
		JScrollPane pane = new JScrollPane(mainPanel);
		cards.add(pane, key);
		layout.show(cards, key);
	}
	
	public Map<String, AttributeClass> getPartConfig() {
		// TODO implementation.
		HashMap<String, AttributeClass> res = new HashMap<String, AttributeClass>();
		int amtComps = comps.size()/2;
		for(int i = 0; i < amtComps; i++) {
			int firstIndex = i*2;
			int secondIndex = i*2 + 1;
			String attribute = ((JLabel) comps.get(firstIndex)).getText();
			@SuppressWarnings("unchecked")
			int type = ((JComboBox<String>) comps.get(secondIndex)).getSelectedIndex();
			res.put(attribute, AttributeClass.values()[type]);
		}
		return res;
	}
}
