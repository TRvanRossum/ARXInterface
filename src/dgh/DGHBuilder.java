package dgh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import functions.NumericalMapping;
import functions.TextualMapping;

public class DGHBuilder {
	
	private static DGHBuilder builder = new DGHBuilder();
	private Map<String, List<TextualMapping>> attToTextMaps = new HashMap<String, List<TextualMapping>>();
	private Map<String, List<NumericalMapping>> attToNumberMaps = new HashMap<String, List<NumericalMapping>>();
	
	private DGHBuilder(){}
	
	public static DGHBuilder getInstance() {
		return builder;
	}
	
	public DGH build(DGHInput input) {
		orderTextMapsByAttribute(input);
		orderNumericalMapsByAttribute(input);
		return null;
	}
	
	private void orderTextMapsByAttribute(DGHInput input) {
		List<TextualMapping> mapList = input.getTextualMapping();
		for(TextualMapping map : mapList) {
			List<TextualMapping> existing = attToTextMaps.get(map.getAttributeName());
			if(existing == null) {
				existing = new ArrayList<TextualMapping>();
			}
			existing.add(map);
			attToTextMaps.put(map.getAttributeName(), existing);
		}
	}
	
	private void orderNumericalMapsByAttribute(DGHInput input) {
		List<NumericalMapping> mapList = input.getNumberMapping();
		for(NumericalMapping map : mapList) {
			List<NumericalMapping> existing = attToNumberMaps.get(map.getAttributeName());
			if(existing == null) {
				existing = new ArrayList<NumericalMapping>();
			}
			existing.add(map);
			attToNumberMaps.put(map.getAttributeName(), existing);
		}
	}
}
