package dgh;

public class DGH {
	private DGHNode startNode;
	private DGHInput input;
	
	public DGH(DGHInput input) {
		startNode = new DGHNode(input);
	}
	
	public DGHNode getStart() {
		return startNode;
	}
}
