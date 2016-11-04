package algorithms;

import dgh.AALMode;
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
		if(kAnonDB.isLDiverse(l)){
			return kAnonDB;
		}
		if(!dgh.getStart().getAnonLevels().getMode().equals(AALMode.INSENSITIVE)) {
			throw new RuntimeException("You tried to apply an L-diversity algorithm using a DGH not set for insensitive attributes.");
		}
		return null;
	}

}
