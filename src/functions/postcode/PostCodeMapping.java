package functions.postcode;

import functions.Mapping;

public interface PostCodeMapping extends Mapping {
	public PostCodeMapType getTypeOfMapping();
	public Postcode transform(Postcode postcode);
}
