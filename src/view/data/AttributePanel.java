package view.data;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AttributePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3537722754246043758L;

	public AttributePanel(String[] attributes){
		setLayout(new GridLayout(2, 1));
		String[] attTypes = {"Explicit identifier", "Quasi-identifier",
				"Sensitive attribute", "Insensitive attribute"};
		List<JComboBox<String>> listOfLists = new ArrayList<JComboBox<String>>();
		JPanel panel = new JPanel();
		for(int i = 0; i < attributes.length; i++){
			JComboBox<String> list = new JComboBox<String>(attTypes);
			list.setSelectedIndex(0);
			list.setEditable(false);
			listOfLists.add(list);
		}
		
		panel.setLayout(new GridLayout(attributes.length, 2));
		for(int i = 0; i < attributes.length; i++){
			panel.add(new JLabel(attributes[i]));
			panel.add(listOfLists.get(i));
		}
		add(panel);
		add(new JButton("Apply"));
	}
}
