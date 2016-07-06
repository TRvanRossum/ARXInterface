package data;

public class KAnonDataObject implements DataObject {
	
	private int k;
	private String algorithm;
	private String method;
	
	public KAnonDataObject(int _k, String _alg, String _method) {
		k = _k;
		algorithm = _alg;
		method = _method;
	}
	
	public int getK() {
		return k;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public String getMethod() {
		return method;
	}

	@Override
	public DataType getType() {
		return DataType.K_ANON;
	}

}
