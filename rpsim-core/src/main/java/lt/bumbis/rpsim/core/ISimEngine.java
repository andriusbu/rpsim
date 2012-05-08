package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;

public interface ISimEngine {
	
	void newServiceRequest(String name, IServiceHandler handler);
	void newEvent(long time, TimeUnit timeUnit);

}
