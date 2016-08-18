package dgh.database;

import java.util.List;
import java.util.Map;

import functions.NumericalMapping;
import functions.TextualMapping;
import view.data.config.AttributeClass;
import view.data.config.AttributeType;

/**
 * A database class that allows for easy manipulation of the data.
 * @author Tim
 *
 */
public class DGHDatabase {
	private Map<String, AttributeType> types;
	private Map<String, AttributeClass> classes;
	private List<TextualMapping> textMaps;
	private List<NumericalMapping> numberMaps;
}
