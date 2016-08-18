package functions.postcode;

public class BothLettersPostCodeMapping implements PostCodeMapping {

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
		return PostCodeMapType.BOTHLETTERS;
	}

	@Override
	public Postcode transform(Postcode postcode) {
		Postcode res = postcode.clone();
		res.setFirstLetter('*');
		return res;
	}
}
