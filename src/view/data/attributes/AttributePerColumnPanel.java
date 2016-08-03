package view.data.attributes;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AttributePerColumnPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3066477756764410002L;

	public AttributePerColumnPanel(String[] attributes) {
		update(attributes);
	}
	
	public void update(String[] attributes) {
		removeAll();
		String[] attTypes = {"Explicit identifier", "Quasi-identifier",
				"Sensitive attribute", "Insensitive attribute"};
		List<JComboBox<String>> listOfLists = new ArrayList<JComboBox<String>>();
		for(int i = 0; i < attributes.length; i++){
			JComboBox<String> list = new JComboBox<String>(attTypes);
			list.setSelectedIndex(0);
			list.setEditable(false);
			listOfLists.add(list);
		}
		
		setLayout(new GridLayout(attributes.length, 2));
		for(int i = 0; i < attributes.length; i++){
			add(new JLabel(attributes[i]));
			add(listOfLists.get(i));
		}
	}
}
