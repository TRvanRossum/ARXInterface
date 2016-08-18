package functions.date;

import functions.Mapping;

public interface DateMapping extends Mapping {
	public DateMapType getTypeOfMapping();
	public DateRange transform(DateRange dr);
}
