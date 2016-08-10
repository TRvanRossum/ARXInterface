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
		if(attNam.equals(attributeName) && Arrays.asList(applicableValues).contains(input)) {
			return resultValue;
		}
		else {
			throw new MappingException("Input is not applicable for this map.");
		}
	}
}
