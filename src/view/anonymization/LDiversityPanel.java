package view.anonymization;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.DataObject;

public class LDiversityPanel extends JPanel implements AnonPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8348411091480767181L;
	
	public LDiversityPanel() {
		setLayout(new GridLayout(2, 2));
		add(new JLabel("Value for q (must divide the amount of data in the set)"));
		add(new JLabel("Value for l"));
		JTextField field1 = new JTextField();
		add(field1);
		JTextField field2 = new JTextField();
		add(field2);
	}

	@Override
	public DataObject getData() {
		// TODO Auto-generated method stub
		return null;
	}
}
