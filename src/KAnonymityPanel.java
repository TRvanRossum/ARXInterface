import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KAnonymityPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5085302267149325381L;
	
	private static final String GENERALIZATION = "Generalization";
	private static final String SUPPRESSION = "Suppression";
	
	private static final String MINGEN = "MinGen";
	private static final String DATAFLY = "Datafly";
	private static final String MONDRIAN = "Mondrian";
	private static final String INCOGNITO = "Incognito";
	
	public KAnonymityPanel() {
		setLayout(new GridLayout(2, 3));
		add(new JLabel("Operation type"));
		add(new JLabel("Algorithm used"));
		add(new JLabel("Value for k"));
		JComboBox<String> cb = new JComboBox<String>(new String[]{ GENERALIZATION, SUPPRESSION });
        cb.setEditable(false);
        add(cb);
        JComboBox<String> cb2 = new JComboBox<String>(new String[]{ MINGEN, DATAFLY, MONDRIAN, INCOGNITO });
        cb2.setEditable(false);
        add(cb2);
        JTextField field = new JTextField();
        add(field);
	}
}
