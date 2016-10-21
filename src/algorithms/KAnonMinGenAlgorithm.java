package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dgh.AttributeAnonymityLevel;
import dgh.DGH;
import dgh.DGHException;
import dgh.DGHInput;
import dgh.DGHNode;
import dgh.database.DGHDatabase;

/**
 * The K-anonymity MinGen algorithm.
 * @author Tim van Rossum
 *
 */
public class KAnonMinGenAlgorithm implements Algorithm {
	
	/**
	 * The value for K.
	 */
	private int k;
	/**
	 * The configuration of data, combined with the defined textual and numerical mappings.
	 */
	private DGHInput input;
	/**
	 * A database constructed using the specified input.
	 */
	private DGHDatabase db;
	
	/**
	 * The constructor of the class.
	 * @param _k The value for K.
	 * @param _input The configuration of data, combined with the defined textual and numerical mappings.
	 */
	public KAnonMinGenAlgorithm(int _k, DGHInput _input) {
		k = _k;
		input = _input;
		db = new DGHDatabase(input);
	}
	
	@Override
	public DGHDatabase apply(DGH dgh) {
		if(k == 1) {
			return db;
		}
		if(db.isKAnonymous(k)) {
			return db;
		}
		try {
			db.suppressAllExplicitColumns();
		} catch (DGHException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<DGHNode> nodes = dgh.getStart().generateNeighbours();
		List<DGHDatabase> list = new ArrayList<DGHDatabase>();
		list.add(db);
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
					nextLevelNodes.addAll(node.generateNeighbours());
					if(newDatabase.isKAnonymous(k)) {
						sufficientDB.add(newDatabase);
					}
				}
			}
		}
		db.clear();
		level.clear();
		if(sufficientDB.size() > 0) {
			return selectHighestPrecision(sufficientDB);
		}
		nextLevelDB = this.removeDuplicateDatabases(nextLevelDB);
		nextLevelNodes = this.removeDuplicateNodes(nextLevelNodes);
		return findBestCandidate(nextLevelDB, nextLevelNodes);
	}
	
	/**
	 * Selects the database with the highest precision from a given list.
	 * @param list The given list.
	 * @return The database in the list with the highest precision.
	 */
	private DGHDatabase selectHighestPrecision(List<DGHDatabase> list) {
		// Add some randomness to the outcome.
		Collections.shuffle(list);
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
		DGHNode compare = new DGHNode(db.getLevelOfAnonymization());
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
			return AttributeAnonymityLevel.determineAttributeToAnonymize(db.getLevelOfAnonymization(), n.getAnonLevels());
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
