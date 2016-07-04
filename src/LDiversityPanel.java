import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LDiversityPanel extends JPanel {
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
}
