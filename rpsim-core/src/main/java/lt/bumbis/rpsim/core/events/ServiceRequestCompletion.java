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
    	SvcReq nextRequest = processor.getNextRequest();
        if ( nextRequest != null ) {
        	TimeSpan timeSpan = processor.start(nextRequest);
        	//TODO implement event creation routine in ModelBuilder
        	ServiceRequestCompletion event = new ServiceRequestCompletion(getModel(), "test", false);
        	event.schedule(nextRequest, processor, timeSpan);
        }
    }

}
