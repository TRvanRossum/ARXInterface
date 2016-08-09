package view.attributes;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;

import view.data.config.AttributeType;

public class AttributeRangeTable {
	public AttributeRangeTable() {
		
	}
	
	public List<Component> getComponents(Map<String, AttributeType> attToType){
		List<Component> res = new ArrayList<Component>();
		res.add(new JLabel("Attribute name"));
		res.add(new JLabel("Attribute type"));
		res.add(new JLabel("Values"));
		res.add(new JLabel("Range"));
		return res;
	}
}
