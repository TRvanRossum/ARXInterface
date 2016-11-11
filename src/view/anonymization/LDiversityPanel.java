package view.anonymization;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.DataObject;
import data.LDivDataObject;

public class LDiversityPanel extends JPanel implements AnonPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8348411091480767181L;
	
	private JTextField field1;
	
	private JComboBox<String> box = new JComboBox<String>(new String[]{"Standard", "Entropy"});
	
	public LDiversityPanel() {
		setLayout(new GridLayout(2, 2));
		add(new JLabel("Value for l"));
		add(new JLabel("Method of diversity"));
		field1 = new JTextField();
		add(field1);
		add(box);
	}

	@Override
	public DataObject getData() {
		if(field1.getText().equals("")) {
			return null;
		}
		if(box.getSelectedIndex() == 0){
			return new LDivDataObject(Integer.parseInt(field1.getText()), true);
		}
		return new LDivDataObject(Integer.parseInt(field1.getText()), false);
	}
}
