package dgh.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dgh.AALMode;
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
	
	private AttributeAnonymityLevel levelOfAnonymizationQuasi;
	
	private AttributeAnonymityLevel levelOfAnonymizationInsensitive;
	
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
		levelOfAnonymizationQuasi = new AttributeAnonymityLevel(types, classes, AALMode.QUASI);
		levelOfAnonymizationInsensitive = new AttributeAnonymityLevel(types, classes, AALMode.INSENSITIVE);
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

	public AttributeAnonymityLevel getLevelOfAnonymizationQuasi() {
		return levelOfAnonymizationQuasi;
	}

	public AttributeAnonymityLevel getLevelOfAnonymizationInsensitive() {
		return levelOfAnonymizationInsensitive;
	}

	public Map<String, LinkedList<? extends DGHDataElement>> getDatabase() {
		return database;
	}

	public void setDatabase(Map<String, LinkedList<? extends DGHDataElement>> database) {
		this.database = database;
	}
	
	public void anonymizeColumnQuasi(String attribute) throws DGHException {
		if(types.get(attribute).equals(AttributeType.TEXTUAL)) {
			anonymizeTextColumn(attribute);
		}
		else if(types.get(attribute).equals(AttributeType.NUMERICAL)) {
			anonymizeNumberColumn(attribute);
		}
		else if(types.get(attribute).equals(AttributeType.DATE)) {
			anonymizeDateColumn(attribute, levelOfAnonymizationQuasi.get(attribute));
		}
		else if(types.get(attribute).equals(AttributeType.POSTCODE)) {
			anonymizePostcodeColumn(attribute, levelOfAnonymizationQuasi.get(attribute));
		}
		else {
			throw new DGHException("The given attribute does not appear in this database.");
		}
		levelOfAnonymizationQuasi.increaseLevel(attribute);
	}
	
	public void anonymizeColumnInsensitive(String attribute) throws DGHException {
		if(types.get(attribute).equals(AttributeType.TEXTUAL)) {
			anonymizeTextColumn(attribute);
		}
		else if(types.get(attribute).equals(AttributeType.NUMERICAL)) {
			anonymizeNumberColumn(attribute);
		}
		else if(types.get(attribute).equals(AttributeType.DATE)) {
			anonymizeDateColumn(attribute, levelOfAnonymizationInsensitive.get(attribute));
		}
		else if(types.get(attribute).equals(AttributeType.POSTCODE)) {
			anonymizePostcodeColumn(attribute, levelOfAnonymizationInsensitive.get(attribute));
		}
		else {
			throw new DGHException("The given attribute does not appear in this database.");
		}
		levelOfAnonymizationInsensitive.increaseLevel(attribute);
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
		LinkedList<? extends DGHDataElement> elems = database.get(attribute);
		for(DGHDataElement e : elems) {
			e.suppress();
		}
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
		db.levelOfAnonymizationQuasi = this.levelOfAnonymizationQuasi.clone();
		db.levelOfAnonymizationInsensitive = this.levelOfAnonymizationInsensitive.clone();
		DGHDatabaseCloner cloner = new DGHDatabaseCloner(database);
		db.database = cloner.createCopy();
		return db;
	}
	
	public boolean isKAnonymous(int k) {
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		
		for(int i = 0; i < this.amountOfRows; i++) {
			String insens = this.getRowOfQuasiVals(i);
			if(m.get(insens) == null){
				m.put(insens, 0);
			}
			m.put(insens, m.get(insens) + 1);
		}
		
		for(String s : m.keySet()) {
			if(m.get(s) < k) {
				return false;
			}
		}
		
		return true;
		
		// Optimization technique: keep track of the rows that have been checked to be K-anonymous,
		// and do NOT check them again.
		/*boolean[] check = new boolean[this.amountOfRows];
		for(int index = 0; index < check.length; index++) {
			check[index] = false;
		}
		
		for(int i = 0; i < this.amountOfRows; i++) {
			if(check[i]) {
				continue;
			}
			else {
				int rowsEqual = 0;
				check[i] = true;
				for(int j = i; j < this.amountOfRows; j++) {
					if(areRowsEqual(i,j)) {
						rowsEqual++;
						check[j] = true;
					}
				}
				if(rowsEqual < k) {
					return false;
				}
			}
		}
		return true;*/
	}
	
	/*private boolean areRowsEqual(int i, int j){
		for(String k : this.database.keySet()) {
			if(!this.database.get(k).get(i).equals(this.database.get(k).get(j))){
				return false;
			}
		}
		return true;
	}*/
	
	public double calculatePrecisionOfData() {
		AttributeAnonymityLevel max = AttributeAnonymityLevel.getMaxLevels(types, classes, AALMode.QUASI);
		double sum = 0.0;
		for(String s : levelOfAnonymizationQuasi.keySet()) {
			sum += ((double) levelOfAnonymizationQuasi.get(s)/(double) max.get(s));
		}
		sum = sum / (double) levelOfAnonymizationQuasi.keySet().size();
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
	
	public int hashCode() {
		return levelOfAnonymizationQuasi.hashCode() + levelOfAnonymizationInsensitive.hashCode();
	}
	
	@Override
	public boolean equals(Object other){
		if(other instanceof DGHDatabase) {
			return this.deepEquals((DGHDatabase) other);
		}
		return false;
	}
	
	private boolean deepEquals(DGHDatabase other) {
		return this.levelOfAnonymizationQuasi.equals(other.levelOfAnonymizationQuasi) && this.levelOfAnonymizationInsensitive.equals(other.levelOfAnonymizationInsensitive);
	}
	
	/**
	 * Determine if a database is L-diverse.
	 * @param l The parameter L.
	 * @return true iff the database is L-diverse.
	 */
	public boolean isLDiverse(int l) {
		if(l == 1){
			return true;
		}
		QStarBlock qStar = new QStarBlock();
		
		// Categorize the rows of insensitive attributes.
		for(int i = 0; i < this.amountOfRows; i++){
			qStar.insertInteger(this.getRowOfInsensitiveVals(i), i);
		}
		
		for(String s : qStar.keySet()) {
			Set<String> set = new HashSet<String>();
			List<Integer> list = qStar.get(s);
			for(int index : list) {
				set.add(this.getSensitiveVal(index));
			}
			// Return false if the amount of different sensitive attributes for a specific row of insensitive attributes is
			// less than L.
			if(set.size() < l){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Determine if a database is Entropy-L-diverse.
	 * @param l The parameter L.
	 * @return true iff the database is L-diverse.
	 */
	public boolean isEntropyLDiverse(int l) {
		QStarBlock qStar = new QStarBlock();
		
		double logL = Math.log(l);
		
		// Categorize the rows of insensitive attributes.
		for(int i = 0; i < this.amountOfRows; i++){
			qStar.insertInteger(this.getRowOfInsensitiveVals(i), i);
		}
		
		for(String s : qStar.keySet()) {
			HashMap<String, Double> map = new HashMap<String, Double>();
			double amt = 0;
			for(int i : qStar.get(s)) {
				if(map.get(this.getSensitiveVal(i)) == null) {
					map.put(this.getSensitiveVal(i), 0.0);
				}
				map.put(this.getSensitiveVal(i), map.get(this.getSensitiveVal(i)) + 1.0);
				amt = amt + 1.0;
			}
			double sum = 0.0;
			for(String string : map.keySet()) {
				sum += -Math.log(map.get(string)/amt);
			}
			if(sum < logL){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Calculate the T-closeness of a database.
	 * @return the minimum value t for which it holds that the database is t-close.
	 */
	public double calculateTCloseness() {
		// Group all equivalence classes.
		QStarBlock qStar = new QStarBlock();
		
		for(int i = 0; i < this.amountOfRows; i++){
			qStar.insertInteger(this.getRowOfInsensitiveVals(i), i);
		}
		
		List<Double> list = new ArrayList<Double>(this.amountOfRows);
		for(int i = 0; i < this.amountOfRows; i++) {
			list.add(i, 1.0/((double) this.amountOfRows));
		}
		
		Map<String, List<Double>> equivClasses = new HashMap<String, List<Double>>();
		for(String s : qStar.keySet()) {
			double frac = 1.0/((double) this.amountOfRows);
			List<Double> l = new ArrayList<Double>(this.amountOfRows);
			for(int i = 0; i < this.amountOfRows; i++) {
				if(qStar.get(s).contains(i)) {
					l.add(i, (1.0/((double) qStar.get(s).size())) - frac);
				}
				else {
					l.add(i, -frac);
				}
			}
			equivClasses.put(s, l);
		}
		
		double t = Double.MAX_VALUE;
		
		for(String s : equivClasses.keySet()) {
			t = Math.min(t, determineDistance(equivClasses.get(s)));
		}
		
		return t;
	}
	
	/**
	 * Determine if a database is T-close.
	 * @param t The parameter t.
	 * @return true iff the database is T-close, false otherwise.
	 */
	public boolean isTClose(double t) {
		// Group all equivalence classes.
		QStarBlock qStar = new QStarBlock();
		
		for(int i = 0; i < this.amountOfRows; i++){
			qStar.insertInteger(this.getRowOfInsensitiveVals(i), i);
		}
		
		List<Double> list = new ArrayList<Double>(this.amountOfRows);
		for(int i = 0; i < this.amountOfRows; i++) {
			list.add(i, 1.0/((double) this.amountOfRows));
		}
		
		Map<String, List<Double>> equivClasses = new HashMap<String, List<Double>>();
		for(String s : qStar.keySet()) {
			double frac = 1.0/((double) this.amountOfRows);
			List<Double> l = new ArrayList<Double>(this.amountOfRows);
			for(int i = 0; i < this.amountOfRows; i++) {
				if(qStar.get(s).contains(i)) {
					l.add(i, (1.0/((double) qStar.get(s).size())) - frac);
				}
				else {
					l.add(i, -frac);
				}
			}
			equivClasses.put(s, l);
		}
		
		for(String s : equivClasses.keySet()) {
			if(determineDistance(equivClasses.get(s)) < t) {
				return false;
			}
		}
		
		return true;
	}
	
	private double determineDistance(List<Double> l) {
		if(this.amountOfRows == 1){
			return 0;
		}
		
		double finalFrac = 1.0/((double) (this.amountOfRows - 1));
		double sum = 0.0;
		
		for(int i = 0; i < l.size(); i++) {
			double subsum = 0.0;
			for(int j = i; j < l.size(); j++) {
				subsum += l.get(j);
			}
			sum += Math.abs(subsum);
		}
		
		return finalFrac*sum;
	}
	
	/**
	 * Returns a string representation of the sensitive attribute in the given row.
	 * @param index The index of the row.
	 * @return a string representation of the sensitive attribute.
	 */
	private String getSensitiveVal(int index){
		String res = "";
		for(String key : this.database.keySet()) {
			if(classes.get(key).equals(AttributeClass.SENSITIVE)) {
				return this.database.get(key).get(index).toString();
			}
		}
		res = res.substring(0, res.length() - 1);
		return res;
	}
	
	/**
	 * Returns a string of all the values that are insensitive, separated by commas.
	 * @param index The index of the row.
	 * @return a string of all the values that are insensitive.
	 */
	private String getRowOfInsensitiveVals(int index){
		String res = "";
		for(String key : this.database.keySet()) {
			if(classes.get(key).equals(AttributeClass.INSENSITIVE)) {
				res += this.database.get(key).get(index).toString() + ",";
			}
		}
		res = res.substring(0, res.length() - 1);
		return res;
	}
	
	/**
	 * Returns a string of all the values that are quasi-identifiers, separated by commas.
	 * @param index The index of the row.
	 * @return a string of all the values that are quasi-identifiers.
	 */
	private String getRowOfQuasiVals(int index){
		String res = "";
		for(String key : this.database.keySet()) {
			if(classes.get(key).equals(AttributeClass.QUASI)) {
				res += this.database.get(key).get(index).toString() + ",";
			}
		}
		res = res.substring(0, res.length() - 1);
		return res;
	}
}
