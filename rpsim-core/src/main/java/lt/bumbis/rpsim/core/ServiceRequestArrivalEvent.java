package lt.bumbis.rpsim.core;

import desmoj.core.simulator.Event;

public class ServiceRequestArrivalEvent extends Event<ServiceRequest> {
	
	private SimulationEngine simEngine;

	public ServiceRequestArrivalEvent(SimulationEngine owner, String name, boolean showInTrace) {
		super(owner, name, showInTrace);
		simEngine = owner;
	}

	@Override
	public void eventRoutine(ServiceRequest request) {
		// TODO Implement
		
	}

}
