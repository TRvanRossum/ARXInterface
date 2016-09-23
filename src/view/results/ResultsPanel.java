package view.results;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dgh.database.DGHDatabase;

class ResultsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2767512105398842253L;
	
	private ResultsTable rt;
	private JScrollPane scroll;
	
	ResultsPanel(DGHDatabase db) {
		rt = new ResultsTable(db.transformToDataObject());
		scroll = new JScrollPane(rt.getDataTable());
		add(scroll);
	}
	
	void update(DGHDatabase db) {
		rt.update(db.transformToDataObject());
	}
}
