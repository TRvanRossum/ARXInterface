package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * Singleton DataReader class, in order to read data from files.
 * @author Tim
 *
 */
public class DataReader {
	
	/**
	 * This instance serves as the only possible instance of the DataReader class.
	 */
	private static DataReader dr = new DataReader();
	
	/**
	 * Private basic constructor.
	 */
	private DataReader(){
		
	}
	
	/**
	 * Returns the instance of the DataReader class.
	 * @return The only existing instance.
	 */
	public static DataReader getReader(){
		return dr;
	}
	
	/**
	 * Reads all the data in a given text file, and returns a 
	 * Data object representing this collection
	 * of data.
	 * @param filepath The path to the file with data.
	 * @param delimiter The used delimiter.
	 * @return A Data object representing the collection of data.
	 * @throws IOException If the filepath is incorrect, the data does not contain
	 * the specified delimiters, no delimiter has been specified, and more.
	 */
	public Data readData(String filepath, String delimiter) throws IOException{
		if(delimiter.length() > 1) {
			throw new IOException("The delimiter is more than one character long.");
		}
		
		if(delimiter.length() == 0) {
			throw new IOException("No delimiter entered.");
		}
		
		LineNumberReader lnr = new LineNumberReader(new FileReader(new File(filepath)));
		BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
		lnr.skip(Long.MAX_VALUE);
		int rows = lnr.getLineNumber() - 1;
		lnr.close();
		
		String column = br.readLine();
		if(!delimiterCheck(column, delimiter)) {
			br.close();
			throw new IOException("The first line does not contain the specified delimiters.");
		}
		
		String[] columnData = column.split(delimiter);
		String[][] allData = new String[rows][columnData.length];
		for(int i = 0; i < rows; i++){
			String row = br.readLine();
			if(!delimiterCheck(row, delimiter)){
				br.close();
				throw new IOException("The row " + (i + 1) + " does not have the specified delimiter.");
			}
			allData[i] = row.split(delimiter);
		}
		br.close();
		
		Data res = new Data(columnData, allData);
		return res;
	}
	
	/**
	 * Checks if this string actually contains 
	 * the specified delimiter.
	 * @param input The string to check.
	 * @param delim The specified delimiter, can be only one character long.
	 * @return True if the input contains the delimiter, false otherwise.
	 */
	private boolean delimiterCheck(String input, String delim) {
		if(input.indexOf(delim.charAt(0)) == -1) {
			return false;
		}
		return true;
	}
}
