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
		setLayout(new GridLayout(2, 1));
		add(new JLabel("Value for l"));
		JTextField field = new JTextField();
		add(field);
	}

	@Override
	public DataObject getData() {
		// TODO Auto-generated method stub
		return null;
	}
}
