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
		LineNumberReader lnr = new LineNumberReader(new FileReader(new File(filepath)));
		BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
		lnr.skip(Long.MAX_VALUE);
		int rows = lnr.getLineNumber();
		lnr.close();
		
		String[] columnData = br.readLine().split(delimiter);
		String[][] allData = new String[rows][columnData.length];
		for(int i = 0; i < rows; i++){
			allData[i] = br.readLine().split(delimiter);
		}
		
		br.close();
		
		Data res = new Data(columnData, allData);
		return res;
	}
}
