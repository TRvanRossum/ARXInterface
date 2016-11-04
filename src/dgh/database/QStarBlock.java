package dgh.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used to simulate q*-blocks for L-diversity.
 * @author Tim
 *
 */
public class QStarBlock extends HashMap<String, List<Integer>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5411767049200140392L;
	
	/**
	 * This method allows for the insertion of a single integer.
	 * @param k The key string.
	 * @param i The integer to be inserted.
	 */
	public void insertInteger(String k, int i) {
		List<Integer> l = super.get(k);
		if(l == null) {
			l = new ArrayList<Integer>();
		}
		l.add(i);
		super.put(k, l);
	}

}
