package view.results;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Metrics");
		JMenuItem metricsItem = new JMenuItem("Display metrics");
		metricsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MetricsFrame(db);
			}
			
		});
		
		menu.add(metricsItem);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
		this.add(new ResultsPanel(db));
		
		this.pack();
		
		WindowUtils.centreWindow(this);
	}
	
}
