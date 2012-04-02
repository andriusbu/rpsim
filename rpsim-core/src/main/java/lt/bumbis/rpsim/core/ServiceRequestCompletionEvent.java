package lt.bumbis.rpsim.core;

import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;

public class ServiceRequestCompletionEvent extends Event<ServiceRequest> {

	public ServiceRequestCompletionEvent(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	@Override
	public void eventRoutine(ServiceRequest arg0) {
		// Implement
		
	}

}
