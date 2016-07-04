import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class AnonymizationPanel extends JPanel implements ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5468912575910929580L;
	
	private static final String K_ANON = "k-anonymity";
	private static final String L_DIVERSE = "l-diversity";
	private static final String T_CLOSE = "t-closeness";
	private static final String DIFFERENTIAL = "differential privacy";
	
	private JPanel cards; //a panel that uses CardLayout
	
	public AnonymizationPanel(){
		setLayout(new GridLayout(3, 1));
		String comboBoxItems[] = { K_ANON, L_DIVERSE, T_CLOSE, DIFFERENTIAL };
        JComboBox<String> cb = new JComboBox<String>(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        add(cb);
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(new KAnonymityPanel(), K_ANON);
        cards.add(new LDiversityPanel(), L_DIVERSE);
        cards.add(new TClosenessPanel(), T_CLOSE);
        cards.add(new DifferentialPanel(), DIFFERENTIAL);
        add(cards);
        
        // Create apply button
        JButton apply = new JButton("Apply");
        add(apply);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String) arg0.getItem());
	}
}
