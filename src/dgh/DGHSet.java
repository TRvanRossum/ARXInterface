package dgh;

import java.util.Collection;
import java.util.HashSet;

public class DGHSet extends HashSet<DGHNode>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -598789899672304813L;
	
	public DGHSet() {
		super();
	}
	
	@Override
	public boolean add(DGHNode n) {
		if(!super.contains(n)){
			super.add(n);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean addAll(Collection<? extends DGHNode> coll) {
		boolean res = true;
		for(DGHNode n : coll) {
			res = res && this.add(n);
		}
		res = false;
		return res;
	}
	
}
