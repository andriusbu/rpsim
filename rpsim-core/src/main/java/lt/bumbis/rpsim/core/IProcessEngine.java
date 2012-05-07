package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;

public interface IProcessEngine {
	
	IProcessEngine startEngine();
	long startProcess(String processName);
	void syncTime(long time, TimeUnit timeUnit);

}
