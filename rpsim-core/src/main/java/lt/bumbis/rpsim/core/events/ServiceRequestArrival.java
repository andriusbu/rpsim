package lt.bumbis.rpsim.core.events;

import lt.bumbis.rpsim.core.SimulationEngine;
import lt.bumbis.rpsim.core.entities.ServiceProcessor;
import lt.bumbis.rpsim.core.entities.ServiceRequest;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;

public class ServiceRequestArrival extends Event<ServiceRequest> {
	
	private SimulationEngine simEngine;
    
    public ServiceRequestArrival(Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);
        this.simEngine = (SimulationEngine) owner;
    }

    public void eventRoutine(final ServiceRequest request) {
    	Queue<ServiceProcessor> idleQueue = request.getProcIdleQueue();
    	Queue<ServiceRequest> waitQueue = request.getWaitQueue();
    	waitQueue.insert(request);
        if ( ! idleQueue.isEmpty() ) {
        	ServiceProcessor proc = idleQueue.first();
        	request.setServiceProcessor(proc);
        	idleQueue.remove(proc);
        	waitQueue.remove(request);
        	ServiceRequestCompletion completionEvent = simEngine.getComponentFactory().newServiceRequestCompletionEvent("SR Completion Event", false);
        	completionEvent.schedule(request, request.getDist().sampleTimeSpan(request.getTimeUnit()));
        	
        }
    }

}
