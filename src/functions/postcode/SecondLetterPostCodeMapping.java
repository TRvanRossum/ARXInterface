package functions.postcode;

public class SecondLetterPostCodeMapping implements PostCodeMapping {

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
		return PostCodeMapType.SECONDLETTER;
	}

	@Override
	public Postcode transform(Postcode postcode) {
		Postcode res = postcode.clone();
		res.setSecondLetter('*');
		return res;
	}

}
