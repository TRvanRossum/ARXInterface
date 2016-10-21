package data;
/**
 * The data object for applying K-anonymity.
 * @author Tim van Rossum
 *
 */
public class KAnonDataObject implements DataObject {
	/**
	 * The value for K.
	 */
	private int k;
	/**
	 * The algorithm that is going to be applied.
	 */
	private String algorithm;
	/**
	 * The method, either generalization or suppression.
	 */
	private String method;
	
	/**
	 * Constructor.
	 * @param _k The value for K.
	 * @param _alg The name of the algorithm.
	 * @param _method The method used.
	 */
	public KAnonDataObject(int _k, String _alg, String _method) {
		k = _k;
		algorithm = _alg;
		method = _method;
	}
	
	/**
	 * Returns the value for K.
	 * @return The value for K.
	 */
	public int getK() {
		return k;
	}
	
	/**
	 * Returns the name of the algorithm.
	 * @return The name of the algorithm.
	 */
	public String getAlgorithm() {
		return algorithm;
	}

	/**
	 * Returns the method used.
	 * @return The method used.
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * Returns the type of the data object. In this case, it will always be K_ANON.
	 * @return K_ANON.
	 */
	@Override
	public DataType getType() {
		return DataType.K_ANON;
	}

}
