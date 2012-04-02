package lt.bumbis.rpsim.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import desmoj.core.simulator.Event;

@objid ("12051e28-7ce9-11e1-a49d-028037ec0200")
public class ServiceRequestArrivalEvent extends Event<ServiceRequest> {
    @objid ("12051e29-7ce9-11e1-a49d-028037ec0200")
    private SimulationEngine simEngine;


    @objid ("12051e2a-7ce9-11e1-a49d-028037ec0200")
    public ServiceRequestArrivalEvent(final SimulationEngine owner, final String name, final boolean showInTrace) {
        super(owner, name, showInTrace);
        simEngine = owner;
    }

    @Override
    @objid ("12051e2f-7ce9-11e1-a49d-028037ec0200")
    public void eventRoutine(final ServiceRequest request) {
        // TODO Implement
    }

}
