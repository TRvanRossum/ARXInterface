package dgh;

public class DGHNode {
	
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
	
	public boolean equals(DGHNode other) {
		return anonLevels.equals(other.anonLevels);
	}
}
