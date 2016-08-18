package dgh.database;

import java.util.List;
import java.util.Map;

import dgh.DGHInput;
import functions.NumericalMapping;
import functions.TextualMapping;
import functions.date.DateMapBuilder;
import functions.date.DateMapping;
import functions.postcode.PostCodeMapBuilder;
import functions.postcode.PostCodeMapping;
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
	
	private List<PostCodeMapping> postMaps;
	
	private List<DateMapping> dateMaps;
	
	private int amountOfRows;
	
	public DGHDatabase(DGHInput input) {
		types = input.getConfig().getTypes();
		classes = input.getConfig().getClassification();
		textMaps = input.getTextualMapping();
		numberMaps = input.getNumberMapping();
		postMaps = PostCodeMapBuilder.getInstance().createAllPostCodeMaps();
		dateMaps = DateMapBuilder.getInstance().createAllDateMaps();
		amountOfRows = input.getConfig().getData().getData().length;
	}

	public Map<String, AttributeType> getTypes() {
		return types;
	}

	public void setTypes(Map<String, AttributeType> types) {
		this.types = types;
	}

	public Map<String, AttributeClass> getClasses() {
		return classes;
	}

	public void setClasses(Map<String, AttributeClass> classes) {
		this.classes = classes;
	}

	public List<TextualMapping> getTextMaps() {
		return textMaps;
	}

	public void setTextMaps(List<TextualMapping> textMaps) {
		this.textMaps = textMaps;
	}

	public List<NumericalMapping> getNumberMaps() {
		return numberMaps;
	}

	public void setNumberMaps(List<NumericalMapping> numberMaps) {
		this.numberMaps = numberMaps;
	}

	public List<PostCodeMapping> getPostMaps() {
		return postMaps;
	}

	public void setPostMaps(List<PostCodeMapping> postMaps) {
		this.postMaps = postMaps;
	}

	public List<DateMapping> getDateMaps() {
		return dateMaps;
	}

	public void setDateMaps(List<DateMapping> dateMaps) {
		this.dateMaps = dateMaps;
	}

	public int getAmountOfRows() {
		return amountOfRows;
	}

	public void setAmountOfRows(int amountOfRows) {
		this.amountOfRows = amountOfRows;
	}
}
