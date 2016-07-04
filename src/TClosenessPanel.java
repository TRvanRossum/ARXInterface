import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TClosenessPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8348411091480767181L;
	
	public TClosenessPanel() {
		setLayout(new GridLayout(2, 1));
		JLabel l = new JLabel("Computed value of t: ");
		add(l);
		JButton b = new JButton("Compute t");
		add(b);
	}
}