package view;
import java.awt.GridLayout;

import javax.swing.JPanel;

import io.Data;
import view.data.AttributePanel;
import view.data.DataTable;
import view.data.controller.DataController;

public class DataPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8716864006670971677L;

	private DataController dc;
	
	public DataPanel(String[] attributes) {
		setLayout(new GridLayout(1, 2));
		DataTable t = new DataTable(new Data(attributes, 5));
		AttributePanel p = new AttributePanel(attributes);
		add(t);
		add(p);
		dc = new DataController(p);
		t.setDataController(dc);
	}
	
	public void updateData(Data d) {
		dc.update(d);
	}
}
