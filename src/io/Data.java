package io;

public class Data {
	private String[] attributes;
	private String[][] data;
	
	public Data(String[] _att, String[][] _data){
		attributes = _att;
		data = _data;
	}
	
	public Data(String[] _att, int amtRows){
		attributes = _att;
		data = new String[amtRows][attributes.length];
	}

	public String[] getAttributes() {
		return attributes;
	}

	public String[][] getData() {
		return data;
	}
}
