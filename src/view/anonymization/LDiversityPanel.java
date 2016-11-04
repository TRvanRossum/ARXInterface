package view.anonymization;
import java.awt.GridLayout;

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
	
	public LDiversityPanel() {
		setLayout(new GridLayout(2, 1));
		add(new JLabel("Value for l"));
		field1 = new JTextField();
		add(field1);
	}

	@Override
	public DataObject getData() {
		return new LDivDataObject(Integer.parseInt(field1.getText()));
	}
}
