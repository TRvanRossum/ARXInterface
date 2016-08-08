package view;
import java.awt.GridLayout;

import javax.swing.JPanel;

import io.Data;

import view.data.attributes.MainAttributePanel;
import view.data.config.Configuration;
import view.data.readpanel.DataReadPanel;
import view.data.readpanel.MainDataPanel;

public class DataPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8716864006670971677L;

	private MainDataPanel mdp;
	private MainAttributePanel attPan;
	
	public DataPanel(String[] attributes) {
		setLayout(new GridLayout(2, 2));
		mdp = new MainDataPanel(new Data(attributes, 5));
		attPan = new MainAttributePanel(attributes);
		DataReadPanel dataRead = new DataReadPanel(this);
		add(mdp);
		add(attPan);
		add(dataRead);
	}
	
	public void updateData(Data d) {
		mdp.update(d);
		attPan.update(d.getAttributes());
	}
	
	public Configuration createConfig() {
		return new Configuration(mdp.getData(), attPan.getClassification(), attPan.getTypes());
	}
}
