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
		// TODO
	}
	
	public int getLevel() {
		return anonLevels.getLevel();
	}
	
	public boolean isValidTransition(DGHNode other) {
		int difference = 0;
		if(this.getLevel() + 1 != other.getLevel()) {
			return false;
		}
		
		for(String s : this.anonLevels.keySet()) {
			if(this.anonLevels.get(s) > other.anonLevels.get(s)) {
				return false;
			}
			difference += other.anonLevels.get(s) - this.anonLevels.get(s);
		}
		if(difference == 1) {
			return true;
		}
		return false;
	}
}
