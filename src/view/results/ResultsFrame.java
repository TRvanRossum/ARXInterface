package view.results;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		metricsItem.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MetricsFrame(db);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				new MetricsFrame(db);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
