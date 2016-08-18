package dgh.database;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import view.data.config.AttributeType;

public class DGHDatabaseBuilder {
	
	private String[][] data;
	private String[] attributes;
	private Map<String, AttributeType> types;
	
	public DGHDatabaseBuilder(String[][] _data, String[] _attribs, Map<String, AttributeType> _types) {
		data = _data;
		attributes = _attribs;
		types = _types;
	}

	public Map<String, LinkedList<? extends DGHDataElement>> createDatabase() {
		Map<String, LinkedList<? extends DGHDataElement>> res = new HashMap<String, LinkedList<? extends DGHDataElement>>();
		for(int i = 0; i < attributes.length; i++) {
			String[] column = getDataColumn(i);
			DGHDatabaseColumnBuilder subBuilder = new DGHDatabaseColumnBuilder(column, types.get(attributes[i]), attributes[i]);
			LinkedList<? extends DGHDataElement> transformedColumn = subBuilder.buildColumn();
			res.put(attributes[i], transformedColumn);
		}
		return res;
	}

	private String[] getDataColumn(int colIndex) {
		String[] res = new String[data.length];
		for(int i = 0; i < data.length; i++) {
			res[i] = data[i][colIndex];
		}
		return res;
	}
}
