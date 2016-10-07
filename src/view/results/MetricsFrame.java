package view.results;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dgh.database.DGHDatabase;

public class MetricsFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2787034189792452808L;
	
	MetricsFrame(DGHDatabase db) {
		this.setTitle("Metrics");
		this.setSize(200,200);
		this.add(new JLabel("Precision: "+db.calculatePrecisionOfData()));
	}

}
