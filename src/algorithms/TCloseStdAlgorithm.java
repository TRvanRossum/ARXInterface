package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import dgh.AttributeAnonymityLevel;
import dgh.DGH;
import dgh.DGHException;
import dgh.DGHNode;
import dgh.database.DGHDatabase;

public class TCloseStdAlgorithm implements TCloseAlgorithm {
	
	private double t;
	
	public TCloseStdAlgorithm(double _t) {
		this.t = _t;
	}
	
	@Override
	public DGHDatabase apply(DGHDatabase kAnonDB, DGH dgh, AttributeAnonymityLevel aal) {
		if(this.t <= 0.0) {
			return kAnonDB;
		}
		if(kAnonDB.isTClose(t)) {
			return kAnonDB;
		}
		
		List<DGHNode> nodes;
		if(aal == null) {
			nodes = dgh.getStart().generateNeighbours();
		}
		else {
			DGHNode n = new DGHNode(aal);
			nodes = n.generateNeighbours();
		}
		
		List<DGHDatabase> list = new ArrayList<DGHDatabase>();
		list.add(kAnonDB);
		return findBestCandidate(list, nodes);
	}
	
	/**
	 * A method that allows for breadth-first search in the tree of
	 * anonymization possibilities. It performs breadth-first search,
	 * anonymizes data accordingly, and returns the database which meets
	 * K-anonymity with the highest precision.
	 * @param db
	 * @param level
	 * @return The anonymized database with the highest precision.
	 */
	private DGHDatabase findBestCandidate(List<DGHDatabase> db, List<DGHNode> level) {
		if(level.size() == 0) {
			// If sufficient anonymization is not possible then let the user know.
			// TODO: could use its own exception class.
			throw new RuntimeException("Sufficient diversity was not possible.");
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
						newDatabase.anonymizeColumnInsensitive(att);
					} catch (DGHException e) {
						// Does not happen.
						e.printStackTrace();
					}
					nextLevelDB.add(newDatabase);
					nextLevelNodes.addAll(node.generateNeighbours());
					if(newDatabase.isTClose(t)) {
						sufficientDB.add(newDatabase);
					}
				}
			}
		}
		db.clear();
		level.clear();
		if(sufficientDB.size() > 0) {
			Random r = new Random(System.currentTimeMillis());
			int index = r.nextInt(sufficientDB.size());
			return sufficientDB.get(index);
		}
		nextLevelDB = this.removeDuplicateDatabases(nextLevelDB);
		nextLevelNodes = this.removeDuplicateNodes(nextLevelNodes);
		return findBestCandidate(nextLevelDB, nextLevelNodes);
	}
	
	/**
	 * Checks whether or not the implied transition is valid. A transition
	 * is valid if and only if the database anonymization levels and the
	 * node anonymization levels differ for exactly one attribute, and the difference
	 * should be 1.
	 * @param db The database to be anonymized.
	 * @param n The node to be compared to.
	 * @return true iff the transition is valid.
	 */
	private boolean isValidTransition(DGHDatabase db, DGHNode n) {
		DGHNode compare = new DGHNode(db.getLevelOfAnonymizationInsensitive());
		return compare.isValidTransition(n);
	}
	
	/**
	 * Determines the attribute that needs to be anonymized. Throws an
	 * exception if the transition is not valid.
	 * @param db The database to be anonymized.
	 * @param n The node to be compared to.
	 * @return The string representing the attribute to be anonymized.
	 */
	private String determineAttribute(DGHDatabase db, DGHNode n) {
		if(isValidTransition(db, n)) {
			return AttributeAnonymityLevel.determineAttributeToAnonymize(db.getLevelOfAnonymizationInsensitive(), n.getAnonLevels());
		}
		throw new RuntimeException("The second AAL is not a logical sequel to the first one.");
	}
	
	private List<DGHDatabase> removeDuplicateDatabases(List<DGHDatabase> dbl) {
		Set<DGHDatabase> set = new HashSet<DGHDatabase>();
		set.addAll(dbl);
		List<DGHDatabase> db = new ArrayList<DGHDatabase>();
		db.addAll(set);
		return db;
	}
	
	private List<DGHNode> removeDuplicateNodes(List<DGHNode> nodes) {
		Set<DGHNode> set = new HashSet<DGHNode>();
		set.addAll(nodes);
		List<DGHNode> db = new ArrayList<DGHNode>();
		db.addAll(set);
		return db;
	}

}
