package functions;

public class TextualMappingBuilder {
	
	public TextualMappingBuilder() {
		
	}
	
	public TextualMapping build(String attribute, String input, String output, String delim) throws MapBuildException {
		if(delim.length() == 0) {
			throw new MapBuildException("Please specify a delimiter.");
		}
		if(delim.length() > 1) {
			throw new MapBuildException("The delimiter should be only one character long.");
		}
		String[] values = input.split(delim);
		return new TextualMapping(attribute, output, values);
	}
	
}
