package functions;

public class NumericalMapping implements Mapping{
	
	private String attribute;
	private Range range;
	
	public NumericalMapping(String _attribute, int low, int high) {
		range = new Range(low, high);
	}

	@Override
	public boolean contains(Object o) throws Exception {
		return rangeContains((Integer) o);
	}
	
	private boolean rangeContains(int i) {
		return range.isInRange(i);
	}

	public String getAttribute() {
		return attribute;
	}

}
