package lt.bumbis.rpsim.core;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface IProcessEngine {
	
	IProcessEngine startEngine();
	long startProcess(String processName);
	long startProcess(String processName, Map<String, Object> data);
	void syncTime(long time, TimeUnit timeUnit);

}
