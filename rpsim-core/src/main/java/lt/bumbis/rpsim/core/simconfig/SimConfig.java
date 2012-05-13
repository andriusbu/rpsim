package lt.bumbis.rpsim.core.simconfig;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SimConfig extends ConfigElement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7186985617657581544L;
	
	//Simulation Objects
	private Map<String, Distribution> dists = new HashMap<String, Distribution>();
	private Map<String, ServiceProcessor> svcProcs = new HashMap<String, ServiceProcessor>();
	private Map<String, TokenGenerator> tokenGens = new HashMap<String, TokenGenerator>();
	private Map<String, Activity> activities = new HashMap<String, Activity>();
	private Map<String, TimerEvent> timerEvents = new HashMap<String, TimerEvent>();
	private Map<String, ResourcePool> resPools = new HashMap<String, ResourcePool>();
		 

	@SuppressWarnings("serial")
	private Map<Class<? extends ConfigElement>, HashMap<String, ? extends ConfigElement>> mapping = new HashMap<Class<? extends ConfigElement>, HashMap<String, ? extends ConfigElement>>() {
		{
			put(Distribution.class, (HashMap<String, Distribution>) dists);
			put(ServiceProcessor.class, (HashMap<String, ServiceProcessor>) svcProcs);
			put(TokenGenerator.class, (HashMap<String, TokenGenerator>) tokenGens);
			put(Activity.class, (HashMap<String, Activity>) activities);
			put(TimerEvent.class, (HashMap<String, TimerEvent>) timerEvents);
			put(ResourcePool.class, (HashMap<String, ResourcePool>) resPools);
		}
	};

	public SimConfig(String name, boolean showInReport, boolean showInTrace) {
		super(name, showInReport, showInTrace);
	}

	@SuppressWarnings("unchecked")
	public <T extends ConfigElement> SimConfig add(T element) {
		HashMap<String, T> col = (HashMap<String, T>) mapping.get(element
				.getClass());
		col.put(element.getName(), element);
		return this;
	}
	
	public Map<String, Distribution> getDists() {
		return dists;
	}

	public Map<String, ServiceProcessor> getSvcProcs() {
		return svcProcs;
	}

	public ServiceProcessor getSvcProc(String name) {
		return svcProcs.get(name);
	}
	
	public Map<String, TokenGenerator> getTokenGens() {
		return tokenGens;
	}

	public Map<String, Activity> getActivities() {
		return activities;
	}
	
	public Activity getActivity(String name) {
		return activities.get(name);
	}
	
	public Map<String, TimerEvent> getTimerEvents() {
		return timerEvents;
	}
	
	public TimerEvent getTimerEvent(String name) {
		return timerEvents.get(name);
	}
	
	public Map<String, ResourcePool> getResourcePools() {
		return this.resPools;
	}
	
	public ResourcePool getResourcePool(String name) {
		return resPools.get(name);
	}
}