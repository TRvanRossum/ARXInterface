package dgh;

import java.util.ArrayList;
import java.util.List;

public class DGHNode {
	
	private List<DGHNode> next = new ArrayList<DGHNode>();
	
	public DGHNode() {
		
	}
	
	public DGHNode(List<DGHNode> nextOnes) {
		next = nextOnes;
	}
	
	public List<DGHNode> getNext() {
		return next;
	}

}
