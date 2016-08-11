package dgh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import functions.TextualMapping;

public class DGHBuilder {
	
	private static DGHBuilder builder = new DGHBuilder();
	private Map<String, List<TextualMapping>> attToMaps = new HashMap<String, List<TextualMapping>>();
	
	private DGHBuilder(){}
	
	public static DGHBuilder getInstance() {
		return builder;
	}
	
	public DGH build(DGHInput input) {
		orderTextMapsByAttribute(input);
		return null;
		//return new DGH(attToMaps);
	}
	
	private void orderTextMapsByAttribute(DGHInput input) {
		List<TextualMapping> mapList = input.getTextualMapping();
		for(TextualMapping map : mapList) {
			List<TextualMapping> existing = attToMaps.get(map.getAttributeName());
			if(existing == null) {
				existing = new ArrayList<TextualMapping>();
				existing.add(map);
				attToMaps.put(map.getAttributeName(), existing);
			}
		}
	}
}
