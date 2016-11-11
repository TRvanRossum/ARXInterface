package data;

public class LDivDataObject implements DataObject {
	
	private int l;
	private boolean regular;
	
	public LDivDataObject(int _l, boolean _regular){
		this.regular = _regular;
		this.l = _l;
	}
	
	public int getL(){
		return l;
	}
	
	public boolean getRegular(){
		return this.regular;
	}

	@Override
	public DataType getType() {
		return DataType.L_DIVERSE;
	}

}
