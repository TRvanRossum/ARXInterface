package functions.date;

import java.time.LocalDate;

public class MonthYearDateMapping implements DateMapping {
	
	public MonthYearDateMapping() {}
	
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
		return DateMapType.MONTHYEAR;
	}

	@Override
	public DateRange transform(DateRange dr) {
		int year = dr.getFrom().getYear();
		int month = dr.getFrom().getMonthValue();
		LocalDate newFrom = LocalDate.of(year, month, 1);
		int endOfMonth = determineLastDayOfMonth(newFrom);
		LocalDate newTo = LocalDate.of(year, month, endOfMonth);
		return new DateRange(newFrom, newTo);
	}
	
	private int determineLastDayOfMonth(LocalDate year) {
		if(year.getMonthValue() == 2 && year.isLeapYear()) {
			return 29;
		}
		else if(year.getMonthValue() == 2) {
			return 28;
		}
		else if(year.getMonthValue() == 4 || year.getMonthValue() == 6 || year.getMonthValue() == 9 || year.getMonthValue() == 11) {
			return 30;
		}
		else {
			return 31;
		}
	}
}
