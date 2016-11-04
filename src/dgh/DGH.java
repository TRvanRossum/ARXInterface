package dgh;

public class DGH {
	private DGHNode startNode;
	private DGHInput input;
	
	public DGH(DGHInput _input) {
		startNode = new DGHNode(_input.getConfig().getTypes(), _input.getConfig().getClassification());
		input = _input;
	}

	public DGHNode getStart() {
		return startNode;
	}

	public DGHInput getInput() {
		return input;
	}
}
