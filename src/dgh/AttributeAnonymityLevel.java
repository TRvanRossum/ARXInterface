package dgh;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import view.data.config.AttributeClass;
import view.data.config.AttributeType;
/**
 * This class is an extension of the HashMap<String, Integer> class, and allows for easy
 * tracking of anonymity levels per attribute.
 * @author Tim
 *
 */
public class AttributeAnonymityLevel extends HashMap<String, Integer> {
	
	/**
	 * The map that defines the type of every attribute.
	 */
	private Map<String, AttributeType> types;
	
	/**
	 * The map that defines the class of every attribute.
	 */
	private Map<String, AttributeClass> classes;
	
	/**
	 * The modified key set. This only contains attributes classed as
	 * quasi-identifiers.
	 */
	private Set<String> keySet;
	
	/**
	 * Constructor.
	 * @param _types The map that defines the type per attribute.
	 */
	public AttributeAnonymityLevel(Map<String, AttributeType> _types, Map<String, AttributeClass> _classes) {
		types = _types;
		classes = _classes;
		keySet = new HashSet<String>();
		for(String s : classes.keySet()) {
			if(classes.get(s).equals(AttributeClass.QUASI)) {
				keySet.add(s);
			}
		}
	}
	
	/**
	 * Returns the key set. In this case, it returns the key set of the types variable.
	 */
	@Override
	public Set<String> keySet() {
		return keySet;
	}

	/**
	 * Standard generated serial version UID.
	 */
	private static final long serialVersionUID = 2996858174093036209L;
	
	/**
	 * The overridden get method returns 0 if the result would have been null, otherwise it functions as normal.
	 * @param key The key which is used to return a value.
	 * @return 0 if the result is null, otherwise the result.
	 */
	public int get(String key) {
		if(super.get(key) == null) {
			return 0;
		}
		return super.get(key);
	}
	
	/**
	 * Checks if a specified attribute is at its maximum possible anonymity level (1 for textual
	 * and numerical data, 3 otherwise).
	 * @param attribute The specified attribute.
	 * @return true if the attribute is at its maximum level, false otherwise.
	 */
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
	
	/**
	 * Increases the anonymity level for the specified attribute.
	 * @param attribute The specified attribute.
	 * @throws DGHException If the specified attribute is at its maximum level already.
	 */
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
		AttributeAnonymityLevel res = new AttributeAnonymityLevel(types, classes);
		for(String s : this.keySet()) {
			res.put(s, this.get(s));
		}
		return res;
	}
	
	public static AttributeAnonymityLevel getMaxLevels(Map<String, AttributeType> map, Map<String, AttributeClass> _classes) {
		AttributeAnonymityLevel res = new AttributeAnonymityLevel(map, _classes);
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
	
	public static String determineAttributeToAnonymize(AttributeAnonymityLevel first, AttributeAnonymityLevel second) {
		for(String s : first.keySet()) {
			if(first.get(s) + 1 == second.get(s)) {
				return s;
			}
		}
		throw new RuntimeException("The second AAL is not a logical sequel to the first one.");
	}
}
