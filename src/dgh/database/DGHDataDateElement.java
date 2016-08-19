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

}
