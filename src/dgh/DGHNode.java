package dgh;

import java.util.ArrayList;
import java.util.List;

public class DGHNode {
	
	private List<DGHNode> next = new ArrayList<DGHNode>();
	private AttributeAnonymityLevel anonLevels;
	private DGHInput input;
	
	public DGHNode(DGHInput _input) {
		input = _input;
		anonLevels = new AttributeAnonymityLevel();
	}
	
	public DGHNode(DGHInput _input, AttributeAnonymityLevel level) {
		input = _input;
		anonLevels = level;
	}
	
	public DGHNode(List<DGHNode> nextOnes) {
		next = nextOnes;
	}
	
	public List<DGHNode> getNext() {
		return next;
	}

}
