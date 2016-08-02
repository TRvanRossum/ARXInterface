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
	
	private static DataReader dr = new DataReader();
	
	private DataReader(){
		
	}
	
	public static DataReader getReader(){
		return dr;
	}
	
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
		int rows = lnr.getLineNumber();
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
	
	public boolean delimiterCheck(String input, String delim) {
		if(input.indexOf(delim.charAt(0)) == -1) {
			return false;
		}
		return true;
	}
}
