package view.data.config;

import java.util.Map;

import io.Data;

public class Configuration {
	
	private Data data;
	private Map<String, AttributeClass> classification;
	private Map<String, AttributeType> type;
	
	public Configuration(Data d, Map<String, AttributeClass> c, Map<String, AttributeType> t) {
		data = d;
		classification = c;
		type = t;
	}

	public Data getData() {
		return data;
	}

	public Map<String, AttributeClass> getClassification() {
		return classification;
	}
	
	public Map<String, AttributeType> getTypes() {
		return type;
	}
}
