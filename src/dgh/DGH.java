package dgh;

import java.util.ArrayList;
import java.util.List;

public class DGH {
	private DGHNode startNode;
	
	public DGH(DGHInput input) {
		startNode = new DGHNode(input);
	}
	
	public DGHNode getStart() {
		return startNode;
	}
	
	public void startAnonymization(int k) {
		List<DGHNode> firstLevel = new ArrayList<DGHNode>();
		firstLevel.add(startNode);
		startAnonymization(k, firstLevel);
	}
	
	public void startAnonymization(int k, List<DGHNode> level) {
		
	}
}
