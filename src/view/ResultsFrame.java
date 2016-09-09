package view;

import javax.swing.JFrame;

import dgh.database.DGHDatabase;
import utils.WindowUtils;

public class ResultsFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -835001660651488405L;
	
	public ResultsFrame(DGHDatabase db){
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Results of the anonymization");
		this.setResizable(true);
		
		// TODO code for the menu.
		
		
		WindowUtils.centreWindow(this);
	}
	
}
