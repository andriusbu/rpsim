package lt.bumbis.rpsim.core.events; 

import lt.bumbis.rpsim.core.entities.SvcProcessor;
import lt.bumbis.rpsim.core.entities.SvcReq;
import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

public class ServiceRequestArrival extends EventOf2Entities<SvcReq, SvcProcessor> {
		
  	public ServiceRequestArrival(Model model, String name, boolean showInTrace) {
        super(model, name, showInTrace);
    }

    @Override
    public void eventRoutine(SvcReq request, SvcProcessor processor) {
    	processor.add(request);
    	if ( processor.isAvailable() ) {
    		TimeSpan timeSpan = processor.start(request);
    		//TODO implement event creation routine in ModelBuilder
    		ServiceRequestCompletion event = new ServiceRequestCompletion(getModel(), "SR_Completion", false);
        	event.schedule(request, processor, timeSpan);
        }
    }
}
