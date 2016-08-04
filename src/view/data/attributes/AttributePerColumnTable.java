package view.data.attributes;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class AttributePerColumnTable {
	
	public AttributePerColumnTable() {
		
	}
	
	public List<Component> getUpdatedGrid(String[] attributes) {
		String[] attTypes = {"Explicit identifier", "Quasi-identifier",
				"Sensitive attribute", "Insensitive attribute"};
		List<Component> res = new ArrayList<Component>();
		
		for(int i = 0; i < attributes.length; i++){
			res.add(new JLabel(attributes[i]));
			JComboBox<String> list = new JComboBox<String>(attTypes);
			list.setSelectedIndex(0);
			list.setEditable(false);
			res.add(list);
		}
		
		return res;
	}
	
}
