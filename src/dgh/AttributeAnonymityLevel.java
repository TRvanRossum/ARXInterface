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
		if(this.keySet().containsAll(other.keySet()) && other.keySet().containsAll(this.keySet())) {
			for(String s : this.keySet()) {
				if(this.get(s) != other.get(s)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public int getLevel() {
		int level = 0;
		for(String s : keySet()) {
			level += get(s);
		}
		return level;
	}
}
