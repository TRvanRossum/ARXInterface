package dgh.database;

import java.time.LocalDate;
import java.util.List;

import functions.Mapping;
import functions.date.DateMapping;
import functions.date.DateRange;

public class DGHDataDateElement implements DGHDataElement {
	
	private String attribute;
	private DateRange data;
	
	public DGHDataDateElement(String _att, String dateString) {
		attribute = _att;
		LocalDate date = LocalDate.parse(dateString);
		data = new DateRange(date, date);
	}
	
	private DGHDataDateElement(String _att, DateRange _range) {
		attribute = _att;
		data = _range;
	}
	
	@Override
	public String getAttribute() {
		return attribute;
	}

	@Override
	public String getData() {
		return data.toString();
	}

	@Override
	public void transform(List<? extends Mapping> maps) {
		for(Mapping m : maps) {
			if(m instanceof DateMapping) {
				DateMapping dm = (DateMapping) m;
				try {
					data = dm.transform(data);
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}
	
	public DGHDataDateElement clone() {
		return new DGHDataDateElement(attribute.toString(), data.clone());
	}
	
	public String toString() {
		return getData();
	}

	@Override
	public void suppress() {
		data = new DateRange(LocalDate.MIN, LocalDate.MAX);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof DGHDataDateElement) {
			return this.deepEquals((DGHDataDateElement) o);
		}
		return false;
	}
	
	private boolean deepEquals(DGHDataDateElement o) {
		return this.data.dateRangeEquals(o.data);
	}

}
