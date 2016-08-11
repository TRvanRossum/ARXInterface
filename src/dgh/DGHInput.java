package dgh;

import java.util.List;

import functions.TextualMapping;
import view.data.config.Configuration;

public class DGHInput {
	
	private Configuration config;
	private List<TextualMapping> textualMapping;
	
	public DGHInput(Configuration _config, List<TextualMapping> _textualMapping) {
		config = _config;
		textualMapping = _textualMapping;
	}

	public Configuration getConfig() {
		return config;
	}

	public List<TextualMapping> getTextualMapping() {
		return textualMapping;
	}
}
