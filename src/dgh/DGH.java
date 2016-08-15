package dgh;

import java.util.HashMap;
import java.util.Map;


public class DGH {
	private DGHNode startNode;
	private DGHInput input;
	
	public DGH(DGHInput _input) {
		startNode = new DGHNode(_input.getConfig().getTypes());
		input = _input;
	}

	public DGHNode getStart() {
		return startNode;
	}
	
	public void generate() {
		startNode.generateNeighbours();
	}
}
