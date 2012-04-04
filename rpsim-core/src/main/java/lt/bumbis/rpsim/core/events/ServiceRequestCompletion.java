package lt.bumbis.rpsim.core.events;

import lt.bumbis.rpsim.core.entities.ServiceRequest;

import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;

public class ServiceRequestCompletion extends Event<ServiceRequest> {

    public ServiceRequestCompletion(Model model, String name, boolean showInTrace) {
        super(model, name, showInTrace);
    }

    public void eventRoutine(final ServiceRequest arg0) {
        // TODO Implement eventRoutrine()
    }

}
