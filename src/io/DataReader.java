package io;
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
}
