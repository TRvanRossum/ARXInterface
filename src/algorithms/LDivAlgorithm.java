package algorithms;

import dgh.DGH;
import dgh.database.DGHDatabase;

public interface LDivAlgorithm extends Algorithm {
	/**
	 * Apply the algorithm on the data, taking into consideration the supplied DGH and the already K-anonymized database.
	 * @param dgh The supplied DGH.
	 * @return The most accurate database, with anonymized insensitive attributes.
	 */
	public DGHDatabase apply(DGHDatabase kAnonDB, DGH dgh); 
}
