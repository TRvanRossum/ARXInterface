import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Interface extends JFrame implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5529336786288984928L;
	
	private JPanel superPanel = new JPanel();
	private JPanel cards = new JPanel(new CardLayout());
	private final String ATTRIBUTE_PANEL = "Attributes";
	private final String ANONYMIZATION_PANEL = "Anonymization";
	
	
	public Interface() {
		setTitle("ARX Interface Alpha");
		setVisible(true);
		addCards();
		getContentPane().add(superPanel);
		getContentPane().add(cards, BorderLayout.CENTER);
		pack();
		setSize(480, 240);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addCards() {
		JPanel comboBoxPane = new JPanel();
		String[] items = {ATTRIBUTE_PANEL, ANONYMIZATION_PANEL};
		JComboBox<String> cb = new JComboBox<String>(items);
		cb.setEditable(false);
		cb.addItemListener(this);
		comboBoxPane.add(cb);
		
		JPanel card1 = new AttributePanel();
		
		cards.add(card1, ATTRIBUTE_PANEL);
		
		superPanel.add(comboBoxPane, BorderLayout.PAGE_START);
		superPanel.add(cards, BorderLayout.CENTER);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		CardLayout layout = (CardLayout) cards.getLayout();
		layout.show(cards, (String) arg0.getItem());
	}	
}