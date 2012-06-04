package lt.bumbis.rpsim.demo;

import java.util.HashMap;
import java.util.Map;

import lt.bumbis.rpsim.core.IDataProvider;

public class DataProviderImpl implements IDataProvider {
	
	private int id = 0;
	private Map<String, Object> data;

	public void init() {		
	}
	
	public void prpareData() {
		data = generateData();		
	}

	public Map<String, Object> getProcessData() {
		Map<String, Object> procData = new HashMap<String, Object>();
		procData.put("request", ((Request)data.get("request")).getId());
		return procData;
	}

	public Map<String, Object> getContextData() {
		return data;
	}
	
	private Map<String, Object> generateData() {
		id++;
		Request request = new Request(id + "");
		request.setPersonId("Person_" + id);
		request.setAmount(getRandomNum(1000, 10000));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("request", request);
		return params;
	}
	
	private long getRandomNum(long from, long to) {
		return (long)300;
	}
}
