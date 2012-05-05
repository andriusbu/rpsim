package lt.bumbis.rpsim.core.events; 

import lt.bumbis.rpsim.core.entities.SvcProcessor;
import lt.bumbis.rpsim.core.entities.SvcReq;
import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;

public class ServiceRequestArrival extends EventOf2Entities<SvcReq, SvcProcessor> {
		
  	public ServiceRequestArrival(Model model, String name, boolean showInTrace) {
        super(model, name, showInTrace);
    }

    @Override
    public void eventRoutine(SvcReq request, SvcProcessor processor) {
    	processor.add(request);
    	if ( processor.isAvailable() ) {
    		processor.start(request);
        	//TODO Completion event      	
        }
    }
}
