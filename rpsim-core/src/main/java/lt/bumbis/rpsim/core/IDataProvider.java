package lt.bumbis.rpsim.core;

import java.util.Map;

public interface IDataProvider {

	void init();
	void prpareData();
	Map<String, Object> getProcessData();
	Map<String, Object> getContextData();
	
}
