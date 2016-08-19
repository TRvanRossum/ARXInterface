package dgh.database;

import java.util.List;

import functions.Mapping;

public interface DGHDataElement {
	public String getAttribute();
	public String getData();
	public void transform(List<? extends Mapping> maps);
}
