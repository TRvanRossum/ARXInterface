package view.data.controller;

import io.Data;
import view.data.AttributePanel;

public class DataController {
	
	private AttributePanel attributes;
	
	public DataController(AttributePanel ap) {
		attributes = ap;
	}
	
	public void update(Data d) {
		attributes.update(d);
	}
	
}
