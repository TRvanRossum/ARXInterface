package dgh.database;

import java.util.LinkedList;

import view.data.config.AttributeType;

public class DGHDatabaseColumnBuilder {
	
	private String[] data;
	private String attribute;
	private AttributeType type;
	
	public DGHDatabaseColumnBuilder(String[] _data, AttributeType _type, String _att) {
		attribute = _att;
		data = _data;
		type = _type;
	}
	
	public LinkedList<? extends DGHDataElement> buildColumn() {
		if(type.equals(AttributeType.TEXTUAL)) {
			return buildTextColumn();
		}
		else if(type.equals(AttributeType.NUMERICAL)) {
			return buildNumberColumn();
		}
		else if(type.equals(AttributeType.DATE)) {
			return buildDateColumn();
		}
		else if(type.equals(AttributeType.POSTCODE)){
			return buildPostcodeColumn();
		}
		return null;
	}
	
	private LinkedList<DGHDataPostcodeElement> buildPostcodeColumn() {
		LinkedList<DGHDataPostcodeElement> res = new LinkedList<DGHDataPostcodeElement>();
		for(int i = 0; i < data.length; i++) {
			res.add(new DGHDataPostcodeElement(attribute, data[i]));
		}
		return res;
	}

	private LinkedList<DGHDataDateElement> buildDateColumn() {
		LinkedList<DGHDataDateElement> res = new LinkedList<DGHDataDateElement>();
		for(int i = 0; i < data.length; i++) {
			res.add(new DGHDataDateElement(attribute, data[i]));
		}
		return res;
	}

	private LinkedList<DGHDataNumberElement> buildNumberColumn() {
		LinkedList<DGHDataNumberElement> res = new LinkedList<DGHDataNumberElement>();
		for(int i = 0; i < data.length; i++) {
			int number = Integer.parseInt(data[i]);
			res.add(new DGHDataNumberElement(attribute, number));
		}
		return res;
	}

	private LinkedList<DGHDataTextElement> buildTextColumn() {
		LinkedList<DGHDataTextElement> res = new LinkedList<DGHDataTextElement>();
		for(int i = 0; i < data.length; i++) {
			res.add(new DGHDataTextElement(attribute, data[i]));
		}
		return res;
	}
	
}
