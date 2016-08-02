import javax.swing.JPanel;

import io.Data;

public class DataTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1302255224765149924L;
	
	public DataTable(Data d) {
		String[] atts = d.getAttributes();
		String[][] data = d.getData();
		
	}
}
