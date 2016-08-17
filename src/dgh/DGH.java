package dgh;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		List<DGHNode> nextLevel = startNode.generateNeighbours();
		startNode.setNext(nextLevel);
		generate(nextLevel);
	}
	
	public void generate(List<DGHNode> curLevel) {
		System.out.println(curLevel);
		if(curLevel.size() == 0) {
			return;
		}
		Set<DGHNode> set = new HashSet<DGHNode>();
		for(DGHNode node : curLevel) {
			set.addAll(node.generateNeighbours());
		}
		System.out.println(checkDuplicates(set));
		for(DGHNode node : curLevel) {
			for(DGHNode nextNode : set) {
				if(node.isValidTransition(nextNode)){
					node.addNext(nextNode);
				}
			}
		}
		List<DGHNode> nextLevelNodes = new ArrayList<DGHNode>();
		nextLevelNodes.addAll(set);
		generate(nextLevelNodes);
	}

	public DGHInput getInput() {
		return input;
	}
	
	private boolean checkDuplicates(Set<DGHNode> s) {
		for(DGHNode n : s) {
			int i = 0;
			for(DGHNode n2 : s) {
				if(n.equals(n2)) {
					i++;
				}
			}
			System.out.println(i);
			if(i > 1){
				return true;
			}
		}
		return false;
	}
}
