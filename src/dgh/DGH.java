package dgh;

import java.util.HashMap;
import java.util.Map;

import view.data.config.AttributeType;

public class DGH {
	private DGHNode startNode;
	private DGHInput input;
	private Map<String, Integer> maxLevelPerAttribute = new HashMap<String, Integer>();
	
	public DGH(DGHInput _input) {
		startNode = new DGHNode();
		input = _input;
		determineMaxLevelPerAttribute();
	}
	
	private void determineMaxLevelPerAttribute() {
		Map<String, AttributeType> attTypeMap = input.getConfig().getTypes();
		for(String s : attTypeMap.keySet()) {
			int maxLevel = determineMaxLevel(attTypeMap.get(s));
			maxLevelPerAttribute.put(s, maxLevel);
		}
	}

	private int determineMaxLevel(AttributeType attributeType) {
		if(attributeType.equals(AttributeType.TEXTUAL) || attributeType.equals(AttributeType.NUMERICAL)) {
			return 1;
		}
		if(attributeType.equals(AttributeType.DATE)) {
			return 2;
		}
		return 6;
	}

	public DGHNode getStart() {
		return startNode;
	}
	
	public void generate() {
		startNode.generateNeighbours();
	}
}
