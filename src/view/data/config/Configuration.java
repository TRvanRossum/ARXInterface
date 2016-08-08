package view.data.config;

import java.util.Map;

import io.Data;

public class Configuration {
	
	private Data data;
	private Map<String, AttributeType> classification;
	private Map<String, String> type;
	
	public Configuration(Data d, Map<String, AttributeType> c, Map<String, String> t) {
		data = d;
		classification = c;
		type = t;
	}

	public Data getData() {
		return data;
	}

	public Map<String, AttributeType> getClassification() {
		return classification;
	}
	
	public Map<String, String> getTypes() {
		return type;
	}
}
