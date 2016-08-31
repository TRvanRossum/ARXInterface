package algorithms;

import java.util.List;

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
		double prec = Double.MIN_VALUE;
		DGHDatabase res = null;
		for(DGHDatabase db : list) {
			if(db.calculatePrecisionOfData() > prec) {
				prec = db.calculatePrecisionOfData();
				res = db;
			}
		}
		return res;
	}
}
