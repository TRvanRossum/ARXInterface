package dgh.database;

import java.util.List;

import functions.Mapping;
import functions.NumericalMapping;
import functions.Range;

public class DGHDataNumberElement implements DGHDataElement {
	private String attribute;
	private Range data;

	public DGHDataNumberElement(String _att, int number) {
		attribute = _att;
		data = new Range(number, number);
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
	public void transform(List<? extends Mapping> maps) {
		for(Mapping m : maps) {
			if(m instanceof NumericalMapping) {
				NumericalMapping nm = (NumericalMapping) m;
				try {
					if(nm.getAttributeName().equals(attribute) && nm.contains(attribute)) {
						data = nm.map(attribute, data);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			}
		}
	}
}
