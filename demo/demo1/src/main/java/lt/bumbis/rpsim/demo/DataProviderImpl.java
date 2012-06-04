package lt.bumbis.rpsim.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import lt.bumbis.rpsim.core.IDataProvider;

public class DataProviderImpl implements IDataProvider {
	
	private int id = 0;
	private Map<String, Object> data;
	private Random rand;

	public void init() {
		rand = new Random();
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
		request.setAmount(getRandomNum(100.0, 2000.0));
		
		Person person = new Person("Person_" + id, "Person_" + id + "_name");
		person.setAge((int)getRandomNum(15.0, 65.0));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("request", request);
		params.put("person", person);
		return params;
	}
	
	private long getRandomNum(double from, double to) {
		return (long) (rand.nextDouble() * (to - from) + from);
	}
}
