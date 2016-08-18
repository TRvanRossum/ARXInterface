package functions.postcode;

import functions.Range;

public class Postcode {
	private Range numbers;
	private char firstLetter;
	private char secondLetter;
	
	public Postcode(int _numbers, String letters) {
		numbers = new Range(_numbers, _numbers);
		firstLetter = letters.charAt(0);
		secondLetter = letters.charAt(1);
	}
	
	private Postcode(Range _numbers, char first, char second) {
		numbers = _numbers;
		firstLetter = first;
		secondLetter = second;
	}

	public Range getNumbers() {
		return numbers;
	}

	public void setNumbers(Range numbers) {
		this.numbers = numbers;
	}

	public char getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(char firstLetter) {
		this.firstLetter = firstLetter;
	}

	public char getSecondLetter() {
		return secondLetter;
	}

	public void setSecondLetter(char secondLetter) {
		this.secondLetter = secondLetter;
	}
	
	public String toString() {
		if(numbers.getHighest() == numbers.getLowest()) {
			return numbers.toString() + firstLetter + secondLetter;
		}
		return numbers.toString();
	}
	
	public Postcode clone(){
		return new Postcode(new Range(numbers.getLowest(), numbers.getHighest()), firstLetter, secondLetter);
	}
}
