package dgh;

import java.util.HashMap;
import java.util.Map;

import view.data.config.AttributeType;

public class AttributeAnonymityLevel extends HashMap<String, Integer> {
	
	private Map<String, AttributeType> types;
	
	public AttributeAnonymityLevel(Map<String, AttributeType> _types) {
		types = _types;
	}

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
	
	public boolean isAtMaxLevel(String attribute) {
		int anonLevel = this.get(attribute);
		AttributeType type = types.get(attribute);
		if(anonLevel < 1 && (type.equals(AttributeType.NUMERICAL) || type.equals(AttributeType.TEXTUAL))) {
			return false;
		}
		else if(anonLevel < 3) {
			return false;
		}
		return true;
	}
	
	public void increaseLevel(String attribute) throws DGHException {
		int anonLevel = this.get(attribute);
		if(!this.isAtMaxLevel(attribute)) {
			put(attribute, anonLevel + 1);
		}
		else {
			throw new DGHException("Anonymization level is already at max for attribute: "+attribute);
		}
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
			level += this.get(s);
		}
		return level;
	}
}
