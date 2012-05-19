package lt.bumbis.rpsim.core.simconfig;

import java.util.HashMap;
import java.util.Map;

public abstract class SimConfig extends ConfigElement<SimConfig> {

	//Simulation Objects
	private Map<String, Distribution> dists = new HashMap<String, Distribution>();
	private Map<String, ServiceProcessor> svcProcs = new HashMap<String, ServiceProcessor>();
	private Map<String, TokenGenerator> tokenGens = new HashMap<String, TokenGenerator>();
	private Map<String, Activity> activities = new HashMap<String, Activity>();
	private Map<String, TimerEvent> timerEvents = new HashMap<String, TimerEvent>();
	private Map<String, ResourcePool> resPools = new HashMap<String, ResourcePool>();
	private Map<String, DataProvider> dataProviders = new HashMap<String, DataProvider>();		 

	@SuppressWarnings({ "serial", "rawtypes" })
	private Map<Class<? extends ConfigElement>, HashMap<String, ? extends ConfigElement>> mapping = new HashMap<Class<? extends ConfigElement>, HashMap<String, ? extends ConfigElement>>() {
		{
			put(Distribution.class, (HashMap<String, Distribution>) dists);
			put(ServiceProcessor.class, (HashMap<String, ServiceProcessor>) svcProcs);
			put(TokenGenerator.class, (HashMap<String, TokenGenerator>) tokenGens);
			put(Activity.class, (HashMap<String, Activity>) activities);
			put(TimerEvent.class, (HashMap<String, TimerEvent>) timerEvents);
			put(ResourcePool.class, (HashMap<String, ResourcePool>) resPools);
			put(DataProvider.class, (HashMap<String, DataProvider>) dataProviders);
		}
	};
	
	//---------------------------------------------
	// Constructors
	//---------------------------------------------
	public SimConfig() {
		super();
		setName("Simulation Config");
	}

	public SimConfig(String name, boolean showInReport, boolean showInTrace) {
		super(name, showInReport, showInTrace);
	}
	
	public abstract void configure();
	
	//---------------------------------------------
	// Configuration methods
	//---------------------------------------------
	@SuppressWarnings("unchecked")
	public <T extends ConfigElement<T>> SimConfig add(T element) {
		HashMap<String, T> col = (HashMap<String, T>) mapping.get(element
				.getClass());
		col.put(element.getName(), element);
		return this;
	}
	
	public Map<String, Distribution> getDists() {
		return dists;
	}
	
	public Distribution getDist(String name) {
		return getDists().get(name);
	}

	public Map<String, ServiceProcessor> getSvcProcs() {
		return svcProcs;
	}

	public ServiceProcessor getSvcProc(String name) {
		return getSvcProcs().get(name);
	}
	
	public Map<String, TokenGenerator> getTokenGens() {
		return tokenGens;
	}
	
	public TokenGenerator getTokenGen(String name) {
		return getTokenGens().get(name);
	}

	public Map<String, Activity> getActivities() {
		return activities;
	}
	
	public Activity getActivity(String name) {
		return getActivities().get(name);
	}
	
	public Map<String, TimerEvent> getTimerEvents() {
		return timerEvents;
	}
	
	public TimerEvent getTimerEvent(String name) {
		return getTimerEvents().get(name);
	}
	
	public Map<String, ResourcePool> getResourcePools() {
		return this.resPools;
	}
	
	public ResourcePool getResourcePool(String name) {
		return getResourcePools().get(name);
	}
	
	public Map<String, DataProvider> getDataProviders() {
		return dataProviders;
	}
	
	public DataProvider getDataProvider(String name) {
		return dataProviders.get(name);
	}
}