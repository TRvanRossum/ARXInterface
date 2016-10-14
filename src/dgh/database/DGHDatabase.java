package dgh.database;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dgh.AttributeAnonymityLevel;
import dgh.DGHException;
import dgh.DGHInput;
import functions.NumericalMapping;
import functions.TextualMapping;
import functions.date.DateMapBuilder;
import functions.date.DateMapping;
import functions.postcode.PostCodeMapBuilder;
import functions.postcode.PostCodeMapping;
import io.Data;
import view.data.config.AttributeClass;
import view.data.config.AttributeType;

/**
 * A database class that allows for easy manipulation of the data.
 * @author Tim
 *
 */
public class DGHDatabase {
	
	private String[] attribs;
	
	private Map<String, AttributeType> types;
	
	private Map<String, AttributeClass> classes;
	
	private List<TextualMapping> textMaps;
	
	private List<NumericalMapping> numberMaps;
	
	private List<PostCodeMapping> postMaps;
	
	private List<DateMapping> dateMaps;
	
	private int amountOfRows;
	
	private AttributeAnonymityLevel levelOfAnonymization;
	
	private Map<String, LinkedList<? extends DGHDataElement>> database;
	
	public DGHDatabase(DGHInput input) {
		attribs = input.getConfig().getData().getAttributes();
		types = input.getConfig().getTypes();
		classes = input.getConfig().getClassification();
		textMaps = input.getTextualMapping();
		numberMaps = input.getNumberMapping();
		postMaps = PostCodeMapBuilder.getInstance().createAllPostCodeMaps();
		dateMaps = DateMapBuilder.getInstance().createAllDateMaps();
		amountOfRows = input.getConfig().getData().getData().length;
		levelOfAnonymization = new AttributeAnonymityLevel(types, classes);
		DGHDatabaseBuilder builder = new DGHDatabaseBuilder(input.getConfig().getData().getData(), input.getConfig().getData().getAttributes(), types);
		database = builder.createDatabase();
	}
	
	private DGHDatabase() {}

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

	public AttributeAnonymityLevel getLevelOfAnonymization() {
		return levelOfAnonymization;
	}

	public void setLevelOfAnonymization(AttributeAnonymityLevel levelOfAnonymization) {
		this.levelOfAnonymization = levelOfAnonymization;
	}

	public Map<String, LinkedList<? extends DGHDataElement>> getDatabase() {
		return database;
	}

	public void setDatabase(Map<String, LinkedList<? extends DGHDataElement>> database) {
		this.database = database;
	}
	
	public void anonymizeColumn(String attribute) throws DGHException {
		if(types.get(attribute).equals(AttributeType.TEXTUAL)) {
			anonymizeTextColumn(attribute);
		}
		else if(types.get(attribute).equals(AttributeType.NUMERICAL)) {
			anonymizeNumberColumn(attribute);
		}
		else if(types.get(attribute).equals(AttributeType.DATE)) {
			anonymizeDateColumn(attribute, levelOfAnonymization.get(attribute));
		}
		else if(types.get(attribute).equals(AttributeType.POSTCODE)) {
			anonymizePostcodeColumn(attribute, levelOfAnonymization.get(attribute));
		}
		else {
			throw new DGHException("The given attribute does not appear in this database.");
		}
		levelOfAnonymization.increaseLevel(attribute);
	}
	
	private void anonymizePostcodeColumn(String attribute, int i) {
		LinkedList<? extends DGHDataElement> column = database.get(attribute);
		List<PostCodeMapping> map = new LinkedList<PostCodeMapping>();
		map.add(postMaps.get(i));
		for(int index = 0; index < column.size(); index++) {
			column.get(index).transform(map);
		}
		database.put(attribute, column);
	}

	private void anonymizeDateColumn(String attribute, int i) {
		LinkedList<? extends DGHDataElement> column = database.get(attribute);
		List<DateMapping> map = new LinkedList<DateMapping>();
		map.add(dateMaps.get(i));
		for(int index = 0; index < column.size(); index++) {
			column.get(index).transform(map);
		}
		database.put(attribute, column);
	}

	private void anonymizeNumberColumn(String attribute) {
		LinkedList<? extends DGHDataElement> column = database.get(attribute);
		for(int i = 0; i < column.size(); i++) {
			column.get(i).transform(numberMaps);
		}
		database.put(attribute, column);
	}

	private void anonymizeTextColumn(String attribute) {
		LinkedList<? extends DGHDataElement> column = database.get(attribute);
		for(int i = 0; i < column.size(); i++) {
			column.get(i).transform(textMaps);
		}
		database.put(attribute, column);
	}
	
	public void suppressAllExplicitColumns() throws DGHException{
		for(String s : classes.keySet()) {
			if(classes.get(s).equals(AttributeClass.EXPLICIT)){
				suppressColumn(s);
			}
		}
	}
	
	public void suppressColumn(String attribute) throws DGHException {
		
	}
	
	public DGHDatabase clone() {
		DGHDatabase db = new DGHDatabase();
		db.types = this.types;
		db.attribs = this.attribs;
		db.classes = this.classes;
		db.textMaps = this.textMaps;
		db.numberMaps = this.numberMaps;
		db.postMaps = this.postMaps;
		db.dateMaps = this.dateMaps;
		db.amountOfRows = this.amountOfRows;
		db.levelOfAnonymization = this.levelOfAnonymization.clone();
		DGHDatabaseCloner cloner = new DGHDatabaseCloner(database);
		db.database = cloner.createCopy();
		return db;
	}
	
	public boolean isKAnonymous(int k) {
		for(int i = 0; i < this.amountOfRows; i++) {
			String row = this.getRow(i);
			int rowsEqual = 0;
			for(int j = 0; j < this.amountOfRows; j++) {
				if(row.equals(this.getRow(j))) {
					rowsEqual++;
				}
			}
			if(rowsEqual < k) {
				return false;
			}
		}
		return true;
	}
	
	private String getRow(int index) {
		String res = "";
		for(String key : this.database.keySet()) {
			res += this.database.get(key).get(index).toString() + ",";
		}
		res = res.substring(0, res.length() - 1);
		return res;
	}
	
	public double calculatePrecisionOfData() {
		AttributeAnonymityLevel max = AttributeAnonymityLevel.getMaxLevels(types, classes);
		double sum = 0.0;
		for(String s : levelOfAnonymization.keySet()) {
			sum += ((double) levelOfAnonymization.get(s)/(double) max.get(s));
		}
		sum = sum / (double) levelOfAnonymization.keySet().size();
		return 1.0 - sum;
	}
	
	public Data transformToDataObject() {
		String[][] stringData = new String[amountOfRows][attribs.length];
		for(int i = 0; i < amountOfRows; i++) {
			for(int j = 0; j < attribs.length; j++) {
				stringData[i][j] = getDataElementAt(attribs[j], i);
			}
		}
		Data d = new Data(attribs, stringData);
		return d;
	}
	
	private String getDataElementAt(String attrib, int index) {
		LinkedList<? extends DGHDataElement> firstRes = database.get(attrib);
		return firstRes.get(index).getData();
	}
}
