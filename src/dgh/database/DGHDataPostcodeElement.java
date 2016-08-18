package dgh.database;

import java.util.List;

import functions.Mapping;
import functions.postcode.PostCodeMapping;
import functions.postcode.Postcode;

public class DGHDataPostcodeElement implements DGHDataElement {
	
	private String attribute;
	private Postcode data;
	
	public DGHDataPostcodeElement(String _att, String postcode) {
		attribute = _att;
		int number = Integer.parseInt(postcode.substring(0, 4));
		data = new Postcode(number, postcode.substring(4));
	}

	@Override
	public String getAttribute() {
		return attribute;
	}

	@Override
	public String getData() {
		return data.toString();
	}

	@Override
	public void transform(List<Mapping> maps) {
		for(Mapping m : maps) {
			if(m instanceof PostCodeMapping) {
				PostCodeMapping pm = (PostCodeMapping) m;
				try {
					data = pm.transform(data);
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}

}
