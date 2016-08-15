package dgh;

import java.util.HashMap;

public class AttributeAnonymityLevel extends HashMap<String, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2996858174093036209L;
	
	public int get(String key) {
		if(super.get(key) == null) {
			return 0;
		}
		return super.get(key);
	}
	
	public void increaseLevel(String attribute) {
		put(attribute, get(attribute) + 1);
	}
	
}
