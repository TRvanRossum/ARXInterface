package view.data.config;

import java.util.Map;

import io.Data;

public class Configuration {
	
	private Data data;
	private Map<String, AttributeType> classification;
	
	public Configuration(Data d, Map<String, AttributeType> c) {
		data = d;
		classification = c;
	}

	public Data getData() {
		return data;
	}

	public Map<String, AttributeType> getClassification() {
		return classification;
	}
}
