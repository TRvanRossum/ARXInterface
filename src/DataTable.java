import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import io.Data;

public class DataTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1302255224765149924L;
	
	private String selectedFile = "No file selected.";
	private JLabel selectedFileLabel = new JLabel(selectedFile);
	
	public DataTable(Data d) {
		setLayout(new GridLayout(2, 1));
		String[] atts = d.getAttributes();
		String[][] data = d.getData();
		
		JTable table = new JTable(data, atts){
            /**
			 * 
			 */
			private static final long serialVersionUID = -3803735335044275361L;

			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
		
		table.setRowSelectionAllowed( true );
		table.setColumnSelectionAllowed( true );

		table.setSelectionForeground( Color.white );
		table.setSelectionBackground( Color.red );
		
		table.setEnabled(false);
		
		JScrollPane pane = new JScrollPane(table);
		add(pane);
		add(createDataReaderTable());
	}
	
	private JPanel createDataReaderTable(){
		JPanel res = new JPanel();
		
		res.setLayout(new GridLayout(2, 2));
		
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
		res.add(new JLabel("Data panel, by Tim van Rossum"));
		res.add(new JLabel("Selected file path:"));
		res.add(selectedFileLabel);
		
		return res;
	}
}
