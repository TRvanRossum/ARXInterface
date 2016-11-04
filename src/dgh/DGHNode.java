package dgh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import view.data.config.AttributeClass;
import view.data.config.AttributeType;

public class DGHNode {
	
	private AttributeAnonymityLevel anonLevels;
	private List<DGHNode> next = new ArrayList<DGHNode>();
	
	public DGHNode(Map<String, AttributeType> types, Map<String, AttributeClass> classes, AALMode mode) {
		anonLevels = new AttributeAnonymityLevel(types, classes, mode);
	}
	
	public DGHNode(AttributeAnonymityLevel level) {
		anonLevels = level;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof DGHNode) {
			return this.deepEquals((DGHNode) other);
		}
		return false;
	}
	
	private boolean deepEquals(DGHNode other) {
		return this.anonLevels.equals(other.anonLevels);
	}

	public List<DGHNode> generateNeighbours() {
		List<DGHNode> nextNodes = new ArrayList<DGHNode>();
		// Optimization: calculate the amount of anonymization levels NOT at the maximum level yet.
		// If this is 0, then return an empty list.
		int count = 0;
		for(String key : anonLevels.keySet()) {
			if(!anonLevels.isAtMaxLevel(key)) {
				count++;
			}
		}
		if(count == 0) {
			return nextNodes;
		}
		for(String s : anonLevels.keySet()) {
			if(!anonLevels.isAtMaxLevel(s)) {
				AttributeAnonymityLevel clone = anonLevels.clone();
				try {
					clone.increaseLevel(s);
					nextNodes.add(new DGHNode(clone));
				} catch (DGHException e) {
					// Does not happen.
				}
			}
		}
		return nextNodes;
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
			difference += Math.abs(other.anonLevels.get(s) - this.anonLevels.get(s));
		}
		if(difference == 1) {
			return true;
		}
		return false;
	}

	public List<DGHNode> getNext() {
		return next;
	}

	public void setNext(List<DGHNode> next) {
		this.next = next;
	}
	
	public void addNext(DGHNode node) {
		this.next.add(node);
	}
	public String toString() {
		return "[DGHNode["+anonLevels.toString()+"]]";
	}
	
	public int hashCode() {
		return anonLevels.hashCode();
	}

	public AttributeAnonymityLevel getAnonLevels() {
		return anonLevels;
	}
}
