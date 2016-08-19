package dgh;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import view.data.config.AttributeType;

public class AttributeAnonymityLevel extends HashMap<String, Integer> {
	
	private Map<String, AttributeType> types;
	
	public AttributeAnonymityLevel(Map<String, AttributeType> _types) {
		types = _types;
	}
	
	public Set<String> keySet() {
		return types.keySet();
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
		else if(anonLevel < 3 && (type.equals(AttributeType.DATE) || type.equals(AttributeType.POSTCODE))) {
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
	
	public AttributeAnonymityLevel clone() {
		AttributeAnonymityLevel res = new AttributeAnonymityLevel(types);
		for(String s : this.keySet()) {
			res.put(s, this.get(s));
		}
		return res;
	}
	
	public static AttributeAnonymityLevel getMaxLevels(Map<String, AttributeType> map) {
		AttributeAnonymityLevel res = new AttributeAnonymityLevel(map);
		for(String s : res.keySet()) {
			while(!res.isAtMaxLevel(s)) {
				try {
					res.increaseLevel(s);
				} catch (DGHException e) {
					// Does not happen.
				}
			}
		}
		return res;
	}
}
