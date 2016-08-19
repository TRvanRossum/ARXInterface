package dgh.database;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DGHDatabaseCloner {
	
	private Map<String, LinkedList<? extends DGHDataElement>> original;
	
	public DGHDatabaseCloner(Map<String, LinkedList<? extends DGHDataElement>> _original){
		original = _original;
	}
	
	public Map<String, LinkedList<? extends DGHDataElement>> createCopy() {
		Map<String, LinkedList<? extends DGHDataElement>> res = new HashMap<String, LinkedList<? extends DGHDataElement>>();
		for(String s : original.keySet()) {
			LinkedList<? extends DGHDataElement> list = original.get(s);
			if(list.get(0) instanceof DGHDataTextElement) {
				res.put(s, copyTextList(list));
			}
			else if(list.get(0) instanceof DGHDataNumberElement) {
				res.put(s, copyNumberList(list));
			}
			else if(list.get(0) instanceof DGHDataDateElement) {
				res.put(s, copyDateList(list));
			}
			else if(list.get(0) instanceof DGHDataPostcodeElement) {
				res.put(s, copyPostcodeList(list));
			}
			else {
				// Placeholder for the future.
			}
		}
		return res;
	}

	private LinkedList<DGHDataPostcodeElement> copyPostcodeList(LinkedList<? extends DGHDataElement> list) {
		LinkedList<DGHDataPostcodeElement> res = new LinkedList<DGHDataPostcodeElement>();
		for(int i = 0; i < list.size(); i++) {
			res.add(((DGHDataPostcodeElement) list.get(i)).clone());
		}
		return res;
	}

	private LinkedList<DGHDataDateElement> copyDateList(LinkedList<? extends DGHDataElement> list) {
		LinkedList<DGHDataDateElement> res = new LinkedList<DGHDataDateElement>();
		for(int i = 0; i < list.size(); i++) {
			res.add(((DGHDataDateElement) list.get(i)).clone());
		}
		return res;
	}

	private LinkedList<DGHDataNumberElement> copyNumberList(LinkedList<? extends DGHDataElement> list) {
		LinkedList<DGHDataNumberElement> res = new LinkedList<DGHDataNumberElement>();
		for(int i = 0; i < list.size(); i++) {
			res.add(((DGHDataNumberElement) list.get(i)).clone());
		}
		return res;
	}

	private LinkedList<DGHDataTextElement> copyTextList(LinkedList<? extends DGHDataElement> list) {
		LinkedList<DGHDataTextElement> res = new LinkedList<DGHDataTextElement>();
		for(int i = 0; i < list.size(); i++) {
			res.add(((DGHDataTextElement) list.get(i)).clone());
		}
		return res;
	}
	
}
