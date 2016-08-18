package functions.date;

import java.time.LocalDate;

public class HalfYearDateMapping implements DateMapping {
	
	public HalfYearDateMapping() {}
	
	@Override
	public boolean contains(Object o) throws Exception {
		return true;
	}

	@Override
	public String getAttributeName() {
		return "Generic";
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
