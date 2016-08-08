package view;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import io.Data;

import view.data.attributes.AttributePerColumnPanel;
import view.data.config.Configuration;
import view.data.readpanel.DataReadPanel;
import view.data.readpanel.MainDataPanel;

public class DataPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8716864006670971677L;

	private MainDataPanel mdp;
	private AttributePerColumnPanel attPan;
	
	public DataPanel(String[] attributes) {
		setLayout(new GridLayout(2, 2));
		mdp = new MainDataPanel(new Data(attributes, 5));
		attPan = new AttributePerColumnPanel(attributes);
		DataReadPanel dataRead = new DataReadPanel(this);
		add(mdp);
		add(attPan);
		add(dataRead);
		add(new JButton("Apply"));
	}
	
	public void updateData(Data d) {
		mdp.update(d);
		attPan.update(d.getAttributes());
	}
	
	public Configuration createConfig() {
		return null;
	}
}
