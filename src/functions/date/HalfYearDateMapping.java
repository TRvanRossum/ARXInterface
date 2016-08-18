package functions.date;

import java.time.LocalDate;

public class HalfYearDateMapping implements DateMapping {

	private String attribute;
	
	public HalfYearDateMapping(String _att) {
		attribute = _att;
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
		return DateMapType.HALFYEAR;
	}

	@Override
	public DateRange transform(DateRange dr) {
		int year = dr.getFrom().getYear();
		int restant = year % 5;
		int newYear = year - restant;
		int newYearPlusFive = newYear + 5;
		LocalDate newFrom = LocalDate.of(newYear, 1, 1);
		LocalDate newTo = LocalDate.of(newYearPlusFive, 12, 31);
		return new DateRange(newFrom, newTo);
	}
	
}
