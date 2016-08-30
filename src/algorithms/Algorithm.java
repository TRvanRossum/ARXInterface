package algorithms;

import dgh.DGH;
import dgh.database.DGHDatabase;

public interface Algorithm {
	public DGHDatabase apply(DGH dgh);
}
