package functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumericalMappingBuilder {
	
	public NumericalMappingBuilder() {}
	
	public List<NumericalMapping> build(String attribute, String input, String delim) throws MapBuildException {
		if(delim.length() == 0) {
			throw new MapBuildException("Please specify a delimiter.");
		}
		if(delim.length() > 1) {
			throw new MapBuildException("The delimiter should be only one character long.");
		}
		String[] inputSplit = input.split(delim);
		List<NumericalMapping> res = new ArrayList<NumericalMapping>();
		for(int i = 0; i < inputSplit.length; i++) {
			String[] rangeVals = inputSplit[i].split("-");
			// DEBUG
			System.out.println(Arrays.deepToString(rangeVals));
			int low = Integer.parseInt(rangeVals[0]);
			int high = Integer.parseInt(rangeVals[1]);
			if(low > high) {
				throw new MapBuildException("The range "+low+"-"+high+" has a negative length.");
			}
			res.add(new NumericalMapping(attribute, low, high));
		}
		return res;
	}

}
