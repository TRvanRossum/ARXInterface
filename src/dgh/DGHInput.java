package dgh;

import java.util.List;

import functions.NumericalMapping;
import functions.TextualMapping;
import view.data.config.Configuration;

public class DGHInput {
	
	private Configuration config;
	private List<TextualMapping> textualMapping;
	private List<NumericalMapping> numberMapping;
	
	public DGHInput(Configuration _config, List<TextualMapping> _textualMapping, List<NumericalMapping> _numberMapping) {
		config = _config;
		textualMapping = _textualMapping;
		numberMapping = _numberMapping;
	}

	public Configuration getConfig() {
		return config;
	}

	public List<TextualMapping> getTextualMapping() {
		return textualMapping;
	}

	public List<NumericalMapping> getNumberMapping() {
		return numberMapping;
	}
}
