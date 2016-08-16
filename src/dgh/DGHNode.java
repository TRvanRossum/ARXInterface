package dgh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import view.data.config.AttributeType;

public class DGHNode {
	
	private AttributeAnonymityLevel anonLevels;
	private List<DGHNode> next = new ArrayList<DGHNode>();
	
	public DGHNode(Map<String, AttributeType> types) {
		anonLevels = new AttributeAnonymityLevel(types);
	}
	
	public DGHNode(AttributeAnonymityLevel level) {
		anonLevels = level;
	}
	
	public boolean equals(DGHNode other) {
		return anonLevels.equals(other.anonLevels);
	}

	public void generateNeighbours() {
		int level = anonLevels.getLevel();	
	}
	
	public boolean isValidTransition(DGHNode other) {
		boolean oneLevelDifferenceCheck = false;
		return true;
	}
}
