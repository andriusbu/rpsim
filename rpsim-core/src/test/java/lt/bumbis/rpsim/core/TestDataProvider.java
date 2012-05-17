package lt.bumbis.rpsim.core;

import java.util.HashMap;
import java.util.Map;

public class TestDataProvider implements IDataProvider {
	
	private boolean initCalled = false;
	private int getDataCount = 0;

	public void init() {
		initCalled = true;
		
	}

	public Map<String, Object> getData() {
		getDataCount++;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("String", "String");
		return data;
	}
	
	public boolean isInitCalled() {
		return initCalled;
	}
	
	public int getDataCount() {
		return getDataCount;
	}

}
