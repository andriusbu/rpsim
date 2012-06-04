package lt.bumbis.rpsim.core;

import java.util.HashMap;
import java.util.Map;

public class TestDataProvider implements IDataProvider {
	
	private boolean initCalled = false;
	private int prepareDataCount = 0;
	private int getProcessDataCount = 0;
	private int getContextDataCount = 0;

	public void init() {
		initCalled = true;
	}
	
	public void prpareData() {
		prepareDataCount++;
		
	}

	public Map<String, Object> getProcessData() {
		getProcessDataCount++;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("String", "String");
		return data;
	}

	public Map<String, Object> getContextData() {
		getContextDataCount++;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("String", "String");
		return data;
	}

	public boolean isInitCalled() {
		return initCalled;
	}

	public int getPrepareDataCount() {
		return prepareDataCount;
	}

	public int getGetProcessDataCount() {
		return getProcessDataCount;
	}

	public int getGetContextDataCount() {
		return getContextDataCount;
	}
}
