package lt.bumbis.rpsim.core.events;

import lt.bumbis.rpsim.core.entities.SvcProcessor;
import lt.bumbis.rpsim.core.entities.SvcProcessorExec;
import lt.bumbis.rpsim.core.entities.SvcReq;

import desmoj.core.simulator.Event;
import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;

public class ServiceRequestCompletion extends EventOf2Entities<SvcReq, SvcProcessor> {

    public ServiceRequestCompletion(Model model, String name, boolean showInTrace) {
        super(model, name, showInTrace);
    }

    public void eventRoutine(SvcReq request, SvcProcessor processor) {
    	processor.complete(request);
        if ( processor.haveRequest() ) {
        	SvcReq nextRequest = processor.startNext();
        	//TODO create and schedule completion event
        }
    }

}
