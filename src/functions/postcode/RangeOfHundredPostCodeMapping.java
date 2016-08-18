package functions.postcode;

import functions.Range;

public class RangeOfHundredPostCodeMapping implements PostCodeMapping {

	@Override
	public boolean contains(Object o) throws Exception {
		return true;
	}

	@Override
	public String getAttributeName() {
		return "Generic";
	}

	@Override
	public PostCodeMapType getTypeOfMapping() {
		return PostCodeMapType.RANGEOFHUNDRED;
	}

	@Override
	public Postcode transform(Postcode postcode) {
		Postcode res = postcode.clone();
		int firstFrom = res.getNumbers().getLowest();
		int rest = firstFrom % 100;
		int secondFrom = firstFrom - rest;
		int secondTo = secondFrom + 100;
		res.setNumbers(new Range(secondFrom, secondTo));
		return res;
	}
}
