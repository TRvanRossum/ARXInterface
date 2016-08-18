package functions.postcode;

import java.util.LinkedList;
import java.util.List;

public class PostCodeMapBuilder {
	
	private static final PostCodeMapBuilder INSTANCE = new PostCodeMapBuilder();
	
	private PostCodeMapBuilder() {}
	
	public PostCodeMapBuilder getInstance() {
		return INSTANCE;
	}
	
	public List<PostCodeMapping> createAllPostCodeMaps() {
		List<PostCodeMapping> res = new LinkedList<PostCodeMapping>();
		res.add(new SecondLetterPostCodeMapping());
		res.add(new BothLettersPostCodeMapping());
		res.add(new RangeOfHundredPostCodeMapping());
		return res;
	}
	
}
