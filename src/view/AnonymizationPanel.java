package view;
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
import data.KAnonDataObject;
import data.LDivDataObject;
import view.anonymization.AnonPanel;
import view.anonymization.DifferentialPanel;
import view.anonymization.KAnonymityPanel;
import view.anonymization.LDiversityPanel;
import view.anonymization.TClosenessPanel;

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
	
	private KAnonymityPanel KAnonPanel = new KAnonymityPanel();
	private LDiversityPanel LDivPanel = new LDiversityPanel();
	
	public AnonymizationPanel(JButton _apply){
		setLayout(new GridLayout(3, 1));
		String comboBoxItems[] = { K_ANON, L_DIVERSE, T_CLOSE, DIFFERENTIAL };
        JComboBox<String> cb = new JComboBox<String>(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        add(cb);
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(KAnonPanel, K_ANON);
        cards.add(LDivPanel, L_DIVERSE);
        cards.add(new TClosenessPanel(), T_CLOSE);
        cards.add(new DifferentialPanel(), DIFFERENTIAL);
        currentlyActive = KAnonPanel;
        add(cards);
        
        // Create apply button
        apply = _apply;
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
	
	public int getK() {
		return ((KAnonDataObject) KAnonPanel.getData()).getK();
	}
	
	public int getL() {
		if(LDivPanel.getData() == null) {
			return 0;
		}
		return ((LDivDataObject) LDivPanel.getData()).getL();
	}
}
