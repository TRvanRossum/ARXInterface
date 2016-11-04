package algorithms;

import dgh.DGH;
import dgh.database.DGHDatabase;

public class LDivStandardAlgorithm implements LDivAlgorithm {
	
	private int l;
	
	/**
	 * Constructor.
	 * @param _l The value for L.
	 */
	public LDivStandardAlgorithm(int _l) {
		this.l = _l;
	}
	
	@Override
	public DGHDatabase apply(DGHDatabase kAnonDB, DGH dgh) {
		if(l == 1) {
			return kAnonDB;
		}
		return null;
	}

}
