package functions.date;

import java.time.LocalDate;

public class DateRange {
	private LocalDate from;
	private LocalDate to;
	
	public DateRange(LocalDate _from, LocalDate _to) {
		from = _from;
		to = _to;
	}

	public LocalDate getFrom() {
		return from;
	}

	public LocalDate getTo() {
		return to;
	}
	
	public String toString(){
		return "["+from.toString()+" - "+to.toString()+"]";
	}
	
	public DateRange clone() {
		return new DateRange(from.plusDays(0), to.plusDays(0));
	}
	
	public boolean dateRangeEquals(DateRange other) {
		return this.from.isEqual(other.from) && this.to.isEqual(other.to);
	}
}
