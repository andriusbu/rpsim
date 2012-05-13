package lt.bumbis.rpsim.droolsjbpm;

import lt.bumbis.rpsim.core.IProcessEngine;
import lt.bumbis.rpsim.core.IServiceHandler;
import lt.bumbis.rpsim.core.ISimEngine;

public class TestSimEngine implements ISimEngine {
	
	private long newServiceRequestCounter;
	private long newEventCounter;
	private long newProcessArrivalCounter;
	private long newProcessCompleteionCounter;
	
	private boolean completeWorkItem;
	
	public TestSimEngine(boolean completeWorkItem) {
		this.completeWorkItem = completeWorkItem;
	}

	public void newServiceRequest(String name, IServiceHandler handler) {
		newServiceRequestCounter++;
		if ( completeWorkItem ) {
			handler.update();
		}
	}

	public void newEvent(String eventName) {
		newEventCounter++;
	}

	public void newProcessArrival(String procName, long procInstanceId) {
		newProcessArrivalCounter++;
		
	}

	public void newProcessCompletion(String procName, long procInstanceId) {
		newProcessCompleteionCounter++;
	}

	public long getNewServiceRequestCounter() {
		return newServiceRequestCounter;
	}

	public long getNewEventCounter() {
		return newEventCounter;
	}

	public long getNewProcessArrivalCounter() {
		return newProcessArrivalCounter;
	}

	public long getNewProcessCompleteionCounter() {
		return newProcessCompleteionCounter;
	}

	public void setProcessEngine(IProcessEngine processEngine) {

		
	}
	
	

}
