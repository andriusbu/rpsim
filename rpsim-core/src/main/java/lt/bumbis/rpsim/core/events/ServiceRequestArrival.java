package lt.bumbis.rpsim.core.events;

import lt.bumbis.rpsim.core.entities.ServiceRequest;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;

public class ServiceRequestArrival extends Event<ServiceRequest> {
    
    public ServiceRequestArrival(Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);
    }

    public void eventRoutine(final ServiceRequest request) {
        // TODO Implement
    }

}
