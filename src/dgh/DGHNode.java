package dgh;

import java.util.ArrayList;
import java.util.List;

public class DGHNode {
	
	private AttributeAnonymityLevel anonLevels;
	private List<DGHNode> next = new ArrayList<DGHNode>();
	
	public DGHNode() {
		anonLevels = new AttributeAnonymityLevel();
	}
	
	public DGHNode(AttributeAnonymityLevel level) {
		anonLevels = level;
	}
	
	public boolean equals(DGHNode other) {
		return anonLevels.equals(other.anonLevels);
	}

	public void generateNeighbours() {
		
	}
}
