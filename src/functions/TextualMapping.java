package functions;

import java.util.Arrays;

public class TextualMapping implements Mapping {
	private String attributeName;
	private String resultValue;
	private String[] applicableValues;
	
	public TextualMapping(String attNam, String resVal, String...strings) {
		applicableValues = strings;
		attributeName = attNam;
		resultValue = resVal;
	}
	
	public String map(String attNam, String input) throws MappingException {
		if(attNam.equals(attributeName) && stringContains(input)) {
			return resultValue;
		}
		else {
			throw new MappingException("Input is not applicable for this map.");
		}
	}

	@Override
	public boolean contains(Object o) throws Exception {
		if(o instanceof String) {
			return stringContains((String) o);
		}
		throw new Exception("You tried to check if a non-string was an applicable"
				+ " value for a string-to-string mapping.");
	}

	private boolean stringContains(String s) {
		for(int i = 0; i < applicableValues.length; i++) {
			if(s.equals(applicableValues[i])) {
				return true;
			}
		}
		return false;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public String getResultValue() {
		return resultValue;
	}

	public String[] getApplicableValues() {
		return applicableValues;
	}
	
	public String toString(){
		return attributeName + ": " + Arrays.toString(applicableValues) + " -> " + resultValue;
	}
	
}
