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
	 * The mode of the AAL. This can be either set to quasi-identifiers or to insensitive attributes
	 */
	private AALMode mode;
	
	/**
	 * The modified key set. This only contains attributes classed as
	 * quasi-identifiers.
	 */
	private Set<String> keySet;
	
	/**
	 * Constructor.
	 * @param _types The map that defines the type per attribute.
	 */
	public AttributeAnonymityLevel(Map<String, AttributeType> _types, Map<String, AttributeClass> _classes, AALMode _mode) {
		types = _types;
		classes = _classes;
		mode = _mode;
		keySet = new HashSet<String>();
		for(String s : classes.keySet()) {
			if((classes.get(s).equals(AttributeClass.QUASI) && mode.equals(AALMode.QUASI)) || (classes.get(s).equals(AttributeClass.INSENSITIVE) && mode.equals(AALMode.INSENSITIVE))) {
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
	
	public AALMode getMode(){
		return mode;
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
	
	/**
	 * Check if this AAL equals the other specified AAL. Two AAL's are equal if they have the same key set
	 * and the value for every key is the same in both AAL's.
	 * @param other The other AAL.
	 * @return true iff both AAL's are the same, false otherwise.
	 */
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
	
	/**
	 * Returns the level of the AAL. This is equal to the sum of the values in the AAL.
	 * @return The level of the AAL.
	 */
	public int getLevel() {
		int level = 0;
		for(String s : keySet()) {
			level += this.get(s);
		}
		return level;
	}
	
	/**
	 * Clones this AAL and returns an exact copy.
	 */
	public AttributeAnonymityLevel clone() {
		AttributeAnonymityLevel res = new AttributeAnonymityLevel(types, classes, mode);
		for(String s : this.keySet()) {
			res.put(s, this.get(s));
		}
		return res;
	}
	
	/**
	 * Returns the maximum possible level per attribute.
	 * @param map The mapping of attributes to types.
	 * @param _classes The mapping of attributes to classes.
	 * @return The maximum possible level per attribute.
	 */
	public static AttributeAnonymityLevel getMaxLevels(Map<String, AttributeType> map, Map<String, AttributeClass> _classes, AALMode _mode) {
		AttributeAnonymityLevel res = new AttributeAnonymityLevel(map, _classes, _mode);
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
	
	/**
	 * Determines the attribute to anonymize. This can be traced by finding the attribute for which the value
	 * v_f from the first AAL and v_s from the second AAL, the equation v_f + 1 == v_s holds.
	 * @param first The first AAL.
	 * @param second The second AAL.
	 * @return
	 */
	public static String determineAttributeToAnonymize(AttributeAnonymityLevel first, AttributeAnonymityLevel second) {
		if(!first.mode.equals(second.mode)){
			throw new RuntimeException("The AAL's are not in the same mode.");
		}
		for(String s : first.keySet()) {
			if(first.get(s) + 1 == second.get(s)) {
				return s;
			}
		}
		throw new RuntimeException("The second AAL is not a logical sequel to the first one.");
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
}
