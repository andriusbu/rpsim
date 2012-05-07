package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;

public interface IProcessEngine {
	
	long startProcess(String processName);
	void syncTime(long time, TimeUnit timeUnit);

}
