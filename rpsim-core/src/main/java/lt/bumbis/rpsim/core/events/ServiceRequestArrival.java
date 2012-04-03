package lt.bumbis.rpsim.core.events;

import lt.bumbis.rpsim.core.SimulationEngine;
import lt.bumbis.rpsim.core.entities.ServiceRequest;
import desmoj.core.simulator.Event;

public class ServiceRequestArrival extends Event<ServiceRequest> {
    
    public ServiceRequestArrival(final SimulationEngine owner, final String name, final boolean showInTrace) {
        super(owner, name, showInTrace);
    }

    public void eventRoutine(final ServiceRequest request) {
        // TODO Implement
    }

}
