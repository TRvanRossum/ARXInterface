package view.data;
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
import view.data.readpanel.MainDataPanel;

public class DataTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1302255224765149924L;
	
	private String selectedFile = "No file selected.";
	private JLabel selectedFileLabel = new JLabel(selectedFile);
	private MainDataPanel mdp;
	
	public DataTable(Data d) {
		setLayout(new GridLayout(2, 1));
		
		mdp = new MainDataPanel(d);
		
		add(mdp);
		add(createDataReaderTable());
	}
	
	private JPanel createDataReaderTable(){
		JPanel res = new JPanel();
		
		res.setLayout(new GridLayout(3, 2));
		
		res.add(new JLabel("Selected file path:"));
		res.add(selectedFileLabel);
		res.add(new JLabel("Please type in a delimiter..."));
		JTextField delimField = new JTextField();
		res.add(delimField);
		
		JButton fileChooseButton = new JButton("Choose a file...");
		res.add(fileChooseButton);
		fileChooseButton.addActionListener(new ActionListener() {
			//Handle open button action.
		    public void actionPerformed(ActionEvent e) {
		        final JFileChooser fc = new JFileChooser(); 
		        int returnVal = fc.showOpenDialog(res);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            selectedFile = file.getAbsolutePath();
		            selectedFileLabel.setText(selectedFile);
		            selectedFileLabel.updateUI();
		        }
		    }
		});
		
		JButton applyButton = new JButton("Read all data");
		res.add(applyButton);
		
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
		        	mdp.update(d);
		        }
		    }
		});
		
		return res;
	}
}
