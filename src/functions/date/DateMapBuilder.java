package functions.date;

import java.util.LinkedList;
import java.util.List;

public class DateMapBuilder {
	
	private static final DateMapBuilder INSTANCE = new DateMapBuilder();
	
	private DateMapBuilder(){}
	
	public static DateMapBuilder getInstance() {
		return INSTANCE;
	}
	
	public List<DateMapping> createAllDateMaps() {
		List<DateMapping> res = new LinkedList<DateMapping>();
		res.add(new MonthYearDateMapping());
		res.add(new YearDateMapping());
		res.add(new HalfYearDateMapping());
		return res;
	}
	
}
