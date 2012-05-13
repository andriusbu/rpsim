package lt.bumbis.rpsim.core;

public interface ISimEngine {
	
	void setProcessEngine(IProcessEngine processEngine);
	void newServiceRequest(String name, IServiceHandler handler);
	void newEvent(String eventName);
	void newProcessArrival(String procName, long procInstanceId);
	void newProcessCompletion(String procName, long procInstanceId);
}
