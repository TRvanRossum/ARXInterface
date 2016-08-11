package functions;

public class MapBuildException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5103965841114553459L;

	public MapBuildException() {
		super("Mapping could not be built.");
	}
	
	public MapBuildException(String msg) {
		super(msg);
	}
	
}
