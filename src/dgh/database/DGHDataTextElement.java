package dgh.database;

import java.util.List;

import functions.Mapping;
import functions.TextualMapping;

public class DGHDataTextElement implements DGHDataElement {
	private String attribute;
	private String data;
	
	public DGHDataTextElement(String _att, String _dat) {
		attribute = _att;
		data = _dat;
	}
	
	@Override
	public String getAttribute() {
		return attribute;
	}
	
	@Override
	public String getData() {
		return data;
	}

	@Override
	public void transform(List<? extends Mapping> maps) {
		for(Mapping m : maps) {
			if(m instanceof TextualMapping) {
				TextualMapping tm = (TextualMapping) m;
				try {
					if(tm.getAttributeName().equals(attribute) && tm.contains(data)) {
						data = tm.map(attribute, data);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			}
		}
	}
	
	public DGHDataTextElement clone() {
		return new DGHDataTextElement(attribute.toString(), data.toString());
	}
	
	public String toString() {
		return data;
	}
}
