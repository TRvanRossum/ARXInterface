package view.data.readpanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import io.Data;

public class MainDataPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1809161225399095392L;
	
	private MainDataTable mdt;
	private JScrollPane scroll;
	
	public MainDataPanel(Data d) {
		mdt = new MainDataTable(d);
		scroll = new JScrollPane(mdt.getDataTable());
		add(scroll);
	}
	
	public void update(Data d) {
		mdt.update(d);
	}
}
