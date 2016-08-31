package algorithms;

import java.util.List;

import dgh.AttributeAnonymityLevel;
import dgh.DGH;
import dgh.DGHInput;
import dgh.DGHNode;
import dgh.database.DGHDatabase;

public class KAnonMinGenAlgorithm implements Algorithm {
	
	private int k;
	private DGHInput input;
	private DGHDatabase db;
	
	public KAnonMinGenAlgorithm(int _k, DGHInput _input) {
		k = _k;
		input = _input;
		db = new DGHDatabase(input);
	}
	
	@Override
	public DGHDatabase apply(DGH dgh) {
		if(dgh.getStart().getNext() == null) {
			// if the DGH has not been generated yet, generate it.
			dgh.generate();
		}
		List<DGHNode> nodes = dgh.getStart().getNext();
		// TODO FUCKING HELL I HAVE THE MOST AMAZING IDEA BUT NO IDEA HOW TO IMPLEMENT IT.
		return findBestCandidate(null, null);
	}
	
	private DGHDatabase findBestCandidate(List<DGHDatabase> db, List<DGHNode> level) {
		// TODO FUCKING HELL I HAVE THE MOST AMAZING IDEA BUT NO IDEA HOW TO IMPLEMENT IT.
		// I have no idea what to code and how to code it...................................
		return null;
	}
	
	private DGHDatabase selectHighestPrecision(List<DGHDatabase> list) {
		double prec = -1.0;
		DGHDatabase res = null;
		for(DGHDatabase db : list) {
			if(db.calculatePrecisionOfData() > prec) {
				prec = db.calculatePrecisionOfData();
				res = db;
			}
		}
		return res;
	}
	
	private boolean isValidTransition(DGHDatabase db, DGHNode n) {
		DGHNode compare = new DGHNode(db.getLevelOfAnonymization());
		return compare.isValidTransition(n);
	}
	
	private String determineAttribute(DGHDatabase db, DGHNode n) {
		if(isValidTransition(db, n)) {
			return AttributeAnonymityLevel.determineAttributeToAnonymize(db.getLevelOfAnonymization(), n.getAnonLevels());
		}
		throw new RuntimeException("The second AAL is not a logical sequel to the first one.");
	}
}