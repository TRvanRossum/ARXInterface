import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import data.DataObject;

public class AnonymizationPanel extends JPanel implements ItemListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5468912575910929580L;
	
	private static final String K_ANON = "k-anonymity";
	private static final String L_DIVERSE = "l-diversity";
	private static final String T_CLOSE = "t-closeness";
	private static final String DIFFERENTIAL = "differential privacy";
	
	private JPanel cards; //a panel that uses CardLayout
	private JButton apply;
	private JPanel currentlyActive;
	
	private JPanel KAnonPanel = new KAnonymityPanel();
	
	public AnonymizationPanel(){
		setLayout(new GridLayout(3, 1));
		String comboBoxItems[] = { K_ANON, L_DIVERSE, T_CLOSE, DIFFERENTIAL };
        JComboBox<String> cb = new JComboBox<String>(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        add(cb);
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(KAnonPanel, K_ANON);
        cards.add(new LDiversityPanel(), L_DIVERSE);
        cards.add(new TClosenessPanel(), T_CLOSE);
        cards.add(new DifferentialPanel(), DIFFERENTIAL);
        currentlyActive = KAnonPanel;
        add(cards);
        
        // Create apply button
        apply = new JButton("Apply");
        add(apply);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String) arg0.getItem());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AnonPanel activePanel = (AnonPanel) currentlyActive;
		@SuppressWarnings("unused")
		// TODO need to find a way to send it to the main panel.
		DataObject data = activePanel.getData();
	}
}
