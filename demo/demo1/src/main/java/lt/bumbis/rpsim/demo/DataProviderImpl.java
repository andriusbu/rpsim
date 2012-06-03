package lt.bumbis.rpsim.demo;

import java.util.HashMap;
import java.util.Map;

import lt.bumbis.rpsim.core.IDataProvider;

public class DataProviderImpl implements IDataProvider {
	
	private int id = 0;

	public void init() {		
	}
	
	public Map<String, Object> getData() {
		id++;
		Request request = new Request(id + "");
		request.setPersonId("Person_" + id);
		request.setAmount(getRandomNum(1000, 10000));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("request", request.getId());
		return params;
	}
	
	private long getRandomNum(long from, long to) {
		return (long)1000;
	}
}
