package lt.bumbis.rpsim.core;

import lt.bumbis.rpsim.core.events.ServiceRequestArrival;
import lt.bumbis.rpsim.core.events.ServiceRequestCompletion;

public class ModelComponentFactory {
	
	private SimulationEngine simEngine;
	
	public ModelComponentFactory() {
	}
	
	public ModelComponentFactory(SimulationEngine simEngine) {
		this.simEngine = simEngine;
	}
	
	public ModelComponentFactory connectSimEngine(SimulationEngine simEngine) {
		this.simEngine = simEngine;
		return this;
	}
	
	public ServiceRequestArrival newServiceRequestArivalEvent(String name) {
		return newServiceRequestArivalEvent(name, true);
	}	
	public ServiceRequestArrival newServiceRequestArivalEvent(String name, boolean showInTrace) {
		return new ServiceRequestArrival(simEngine, name, showInTrace);
	}
	
	public ServiceRequestCompletion newServiceRequestCompletionEvent(String name) {
		return newServiceRequestCompletionEvent(name, true);
	}	
	public ServiceRequestCompletion newServiceRequestCompletionEvent(String name, boolean showInTrace) {
		return new ServiceRequestCompletion(simEngine, name, showInTrace);
	}

	
//	public <E extends EventAbstract> E getEvent(String name, Class<E> eventType) {
//		Type type = getClass().getGenericSuperclass();
//		if (type instanceof ParameterizedType ) {
//			ParameterizedType paramType = (ParameterizedType)type;
//			Class<E> eClass = (Class<E>)paramType.getActualTypeArguments()[0];
//			return eClass.newInstance();
//		}
//		return null;		
//	}
	

}
