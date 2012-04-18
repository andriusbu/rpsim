package lt.bumbis.rpsim.core.events;

import lt.bumbis.rpsim.core.entities.SvcReq;

import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;

public class ServiceRequestCompletion extends Event<SvcReq> {

    public ServiceRequestCompletion(Model model, String name, boolean showInTrace) {
        super(model, name, showInTrace);
    }

    public void eventRoutine(final SvcReq request) {
        request.getProcIdleQueue().insert(request.getServiceProcessor());
        request.setServiceProcessor(null);
        //TODO Implement ProcessEngin notification about completed service request
        if (!request.getWaitQueue().isEmpty() ) {
        	//TODO Implement ServiceRequest processing from wait queue
        }
    }

}
