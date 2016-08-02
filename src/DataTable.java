import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import io.Data;
import io.DataReader;

public class DataTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1302255224765149924L;
	
	private String selectedFile = "No file selected.";
	private JLabel selectedFileLabel = new JLabel(selectedFile);
	private JTable dataTable;
	private String[][] data;
	private String[] atts;
	
	public DataTable(Data d) {
		setLayout(new GridLayout(2, 1));
		atts = d.getAttributes();
		data = d.getData();
		
		dataTable = new JTable(data, atts){
            /**
			 * 
			 */
			private static final long serialVersionUID = -3803735335044275361L;

			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
		
		dataTable.setRowSelectionAllowed( true );
		dataTable.setColumnSelectionAllowed( true );

		dataTable.setSelectionForeground( Color.white );
		dataTable.setSelectionBackground( Color.red );
		
		dataTable.setEnabled(false);
		
		JScrollPane pane = new JScrollPane(dataTable);
		add(pane);
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
		        	data = d.getData();
		        	atts = d.getAttributes();
		        	dataTable.repaint();
		        }
		    }
		});
		
		return res;
	}
}
