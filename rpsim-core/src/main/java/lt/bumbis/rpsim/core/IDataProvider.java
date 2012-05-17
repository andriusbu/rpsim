package lt.bumbis.rpsim.core;

import java.util.Map;

public interface IDataProvider {

	void init();
	Map<String, Object> getData();
	
}
