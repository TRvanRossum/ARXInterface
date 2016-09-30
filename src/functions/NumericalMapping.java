package functions;

public class NumericalMapping implements Mapping{
	
	private String attribute;
	private Range range;
	
	public NumericalMapping(String _attribute, int low, int high) {
		attribute = _attribute;
		range = new Range(low, high);
	}

	@Override
	public boolean contains(Object o) throws Exception {
		return rangeContains((Integer) o);
	}
	
	private boolean rangeContains(int i) {
		return range.isInRange(i);
	}

	public String getAttributeName() {
		return attribute;
	}

	public Range map(String attribute2, Range data) throws MappingException {
		if(attribute.equals(attribute2) && range.includes(data)) {
			return range;
		}
		throw new MappingException("You either tried using a map for a different attribute or a map which does not include the specified range.");
	}
	
	public String toString() {
		return attribute + ": " + range.toString();
	}

}
