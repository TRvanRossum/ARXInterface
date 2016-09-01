package algorithms;

import dgh.DGH;
import dgh.database.DGHDatabase;

public interface Algorithm {
	/**
	 * Apply the algorithm on the data, taking into consideration the supplied DGH.
	 * @param dgh The supplied DGH.
	 * @return The most accurate database.
	 */
	public DGHDatabase apply(DGH dgh);
}
