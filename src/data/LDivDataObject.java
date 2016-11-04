package data;

public class LDivDataObject implements DataObject {
	
	private int l;
	
	public LDivDataObject(int _l){
		this.l = _l;
	}
	
	public int getL(){
		return l;
	}

	@Override
	public DataType getType() {
		return DataType.L_DIVERSE;
	}

}
