import javax.swing.JComboBox;
import javax.swing.JPanel;

public class AttributePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3537722754246043758L;

	public AttributePanel(){
		String[] attTypes = {"Explicit identifier", "Quasi-identifier",
				"Sensitive attribute", "Insensitive attribute"};
		JComboBox<String> list = new JComboBox<String>(attTypes);
		list.setSelectedIndex(0);
		list.setEditable(false);
		add(list);
	}
}
