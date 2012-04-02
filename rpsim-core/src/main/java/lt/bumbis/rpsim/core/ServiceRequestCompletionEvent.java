package lt.bumbis.rpsim.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;

@objid ("12051e33-7ce9-11e1-a49d-028037ec0200")
public class ServiceRequestCompletionEvent extends Event<ServiceRequest> {

    @objid ("12051e34-7ce9-11e1-a49d-028037ec0200")
    public ServiceRequestCompletionEvent(final Model arg0, final String arg1, final boolean arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    @objid ("12051e39-7ce9-11e1-a49d-028037ec0200")
    public void eventRoutine(final ServiceRequest arg0) {
        // Implement
    }

}
