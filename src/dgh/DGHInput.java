package dgh;

import java.util.List;

import functions.NumericalMapping;
import functions.TextualMapping;
import view.data.config.Configuration;

/**
 * A data class containing the configuration of data, as well as the defined numerical and
 * textual mappings.
 * @author Tim van Rossum
 *
 */
public class DGHInput {
	/**
	 * The configuration of data.
	 */
	private Configuration config;
	/**
	 * The defined textual mappings.
	 */
	private List<TextualMapping> textualMapping;
	/**
	 * The defined numerical mappings.
	 */
	private List<NumericalMapping> numberMapping;
	
	/**
	 * Constructor.
	 * @param _config The configuration of data.
	 * @param _textualMapping The defined textual mappings.
	 * @param _numberMapping The defined numerical mappings.
	 */
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
