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
		return lowest <= val && highest > val;
	}
	
	public String toString() {
		if(isASingleNumber()) {
			return lowest+"";
		}
		return "["+lowest+" - "+highest+"]";
	}
	
	private boolean isASingleNumber() {
		return lowest == highest;
	}
	
	public boolean includes(Range other) {
		return this.lowest <= other.lowest && this.highest > other.highest;
	}
	
	public Range clone() {
		return new Range(lowest, highest);
	}
}
