package algorithms;

import java.util.ArrayList;
import java.util.List;

import dgh.AttributeAnonymityLevel;
import dgh.DGH;
import dgh.DGHException;
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
		List<DGHDatabase> list = new ArrayList<DGHDatabase>();
		list.add(db);
		return findBestCandidate(list, nodes);
	}
	
	private DGHDatabase findBestCandidate(List<DGHDatabase> db, List<DGHNode> level) {
		if(level.size() == 0) {
			throw new RuntimeException("Sufficient anonymization was not possible.");
		}
		List<DGHDatabase> nextLevelDB = new ArrayList<DGHDatabase>();
		List<DGHNode> nextLevelNodes = new ArrayList<DGHNode>();
		List<DGHDatabase> sufficientDB = new ArrayList<DGHDatabase>();
		for(DGHDatabase database : db) {
			for(DGHNode node : level) {
				if(isValidTransition(database, node)) {
					DGHDatabase newDatabase = database.clone();
					String att = determineAttribute(newDatabase, node);
					try {
						newDatabase.anonymizeColumn(att);
					} catch (DGHException e) {
						// Does not happen.
						e.printStackTrace();
					}
					nextLevelDB.add(newDatabase);
					nextLevelNodes.addAll(node.getNext());
					if(newDatabase.isKAnonymous(k)) {
						sufficientDB.add(newDatabase);
					}
				}
			}
		}
		if(sufficientDB.size() > 0) {
			return selectHighestPrecision(sufficientDB);
		}
		return findBestCandidate(nextLevelDB, nextLevelNodes);
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
