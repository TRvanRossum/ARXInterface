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
	
	public boolean equals(AttributeAnonymityLevel other) {
		if(this.keySet().containsAll(other.keySet())) {
			for(String s : this.keySet()) {
				if(this.get(s) != other.get(s)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
}
