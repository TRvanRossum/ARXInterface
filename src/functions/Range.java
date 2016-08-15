package functions;

public class Range {
	
	private int lowest;
	private int highest;
	
	public Range(int low, int high) {
		lowest = low;
		highest = high;
	}

	public int getLowest() {
		return lowest;
	}

	public int getHighest() {
		return highest;
	}
	
	public boolean isInRange(int val) {
		return lowest <= val && highest >= val;
	}
	
}
