package view.data.readpanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import io.Data;
import io.DataReader;
import utils.WindowUtils;
import view.DataPanel;

public class DataReadPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3281981718958788985L;
	
	private JLabel selectedFileLabel = new JLabel("No file selected.");
	private String selectedFile;
	private DataPanel mainPanel;
	
	public DataReadPanel(DataPanel _mainPanel) {
		
		mainPanel = _mainPanel;
		
		setLayout(new GridLayout(3, 2));
		
		add(new JLabel("Selected file path:"));
		add(selectedFileLabel);
		add(new JLabel("Please type in a delimiter..."));
		JTextField delimField = new JTextField();
		add(delimField);
		
		// Hacky workaround, but it just might work.
		JPanel selfRef = this;
		
		JButton fileChooseButton = new JButton("Choose a file...");
		add(fileChooseButton);
		fileChooseButton.addActionListener(new ActionListener() {
			//Handle open button action.
		    public void actionPerformed(ActionEvent e) {
		        final JFileChooser fc = new JFileChooser(); 
		        int returnVal = fc.showOpenDialog(selfRef);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            selectedFile = file.getAbsolutePath();
		            selectedFileLabel.setText(selectedFile);
		            selectedFileLabel.updateUI();
		        }
		    }
		});
		
		JButton applyButton = new JButton("Read all data");
		add(applyButton);
		
		applyButton.addActionListener(new ActionListener() {
			//Handle open button action.
		    public void actionPerformed(ActionEvent e) {
		        DataReader dr = DataReader.getReader();
		        String delimiter = delimField.getText();
		        Data d = null;
		        try {
					d = dr.readData(selectedFile, delimiter);
				} catch (IOException e1) {
					JFrame frame = new JFrame("Error");
					JPanel panel = new JPanel();
					JLabel label = new JLabel("Error message: "+e1.getMessage());
					panel.add(label);
					frame.add(panel);
					frame.pack();
			        frame.setResizable(false);
			        frame.setVisible(true);
			        WindowUtils.centreWindow(frame);
			        return;
				}
		        finally {
		        	if (d == null) {
		        		return;
		        	}
		        	mainPanel.updateData(d);
		        }
		    }
		});
	}

}
