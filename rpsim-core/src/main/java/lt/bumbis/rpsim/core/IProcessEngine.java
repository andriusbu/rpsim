package lt.bumbis.rpsim.core;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface IProcessEngine {
	
	IProcessEngine startEngine();
	void stopEngine();
	long startProcess(String processName);
	long startProcess(String processName, Map<String, Object> data);
	long startProcess(String processName, Map<String, Object> processData, Map<String, Object> contextData);
	public void addContextData(Object obj);
	void syncTime(long time, TimeUnit timeUnit);

}
