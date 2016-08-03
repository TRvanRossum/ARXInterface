package io;
/**
 * The Data object, designed as a representation of reading the entirety
 * of a text-based dataset.
 * @author Tim
 *
 */
public class Data {
	private String[] attributes;
	private String[][] data;
	
	/**
	 * Basic constructor.
	 * @param _att The attributes of the data.
	 * @param _data The data itself.
	 */
	public Data(String[] _att, String[][] _data){
		attributes = _att;
		data = _data;
	}
	
	/**
	 * Alternative constructor, useful for creating "dummy data"
	 * @param _att The attribute list.
	 * @param amtRows The amount of rows of data.
	 */
	public Data(String[] _att, int amtRows){
		attributes = _att;
		data = new String[amtRows][attributes.length];
	}
	
	/**
	 * Returns the list of attributes.
	 * @return The list of attributes.
	 */
	public String[] getAttributes() {
		return attributes;
	}

	/**
	 * Returns all of the data.
	 * @return The data.
	 */
	public String[][] getData() {
		return data;
	}
}
