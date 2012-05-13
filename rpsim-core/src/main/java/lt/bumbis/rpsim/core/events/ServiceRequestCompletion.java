package lt.bumbis.rpsim.core.events;

import lt.bumbis.rpsim.core.entities.SvcProcessor;
import lt.bumbis.rpsim.core.entities.SvcReq;

import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

public class ServiceRequestCompletion extends EventOf2Entities<SvcReq, SvcProcessor> {

    public ServiceRequestCompletion(Model model, String name, boolean showInTrace) {
        super(model, name, showInTrace);
    }

    public void eventRoutine(SvcReq request, SvcProcessor processor) {
    	processor.complete(request);
    	request.getHandler().update();
    	SvcReq nextRequest = processor.getNextRequest();
        if ( nextRequest != null ) {
        	TimeSpan timeSpan = processor.start(nextRequest);
        	ServiceRequestCompletion event = new ServiceRequestCompletion(getModel(), "SR_Completion", false);
        	event.schedule(nextRequest, processor, timeSpan);
        }
    }

}
