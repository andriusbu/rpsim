package lt.bumbis.rpsim.core;

public interface ISimEngine {
	void newServiceRequest(String name, IServiceHandler handler);
	void newEvent(String eventName);
	void newProcessArrival(String procName);
	void newProcessCompletion(String procName);
}
