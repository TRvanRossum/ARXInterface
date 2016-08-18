package functions.date;

import java.time.LocalDate;

public class YearDateMapping implements DateMapping {

	private String attribute;
	
	public YearDateMapping(String _attribute) {
		attribute = _attribute;
	}
	
	@Override
	public boolean contains(Object o) throws Exception {
		return true;
	}

	@Override
	public String getAttributeName() {
		return attribute;
	}

	@Override
	public DateMapType getTypeOfMapping() {
		return DateMapType.YEAR;
	}

	@Override
	public DateRange transform(DateRange dr) {
		int year = dr.getFrom().getYear();
		LocalDate newFrom = LocalDate.of(year, 1, 1);
		LocalDate newTo = LocalDate.of(year, 12, 31);
		return new DateRange(newFrom, newTo);
	}

}
