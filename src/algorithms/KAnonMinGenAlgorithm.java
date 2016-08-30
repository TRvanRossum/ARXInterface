package algorithms;

import java.util.List;

import dgh.DGH;
import dgh.DGHInput;
import dgh.database.DGHDatabase;

public class KAnonMinGenAlgorithm implements Algorithm {
	
	private int k;
	private DGHInput input;
	
	public KAnonMinGenAlgorithm(int _k, DGHInput _input) {
		k = _k;
		input = _input;
	}
	
	@Override
	public DGHDatabase apply(DGH dgh) {
		
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
