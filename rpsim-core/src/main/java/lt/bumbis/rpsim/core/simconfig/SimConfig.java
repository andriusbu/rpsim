package lt.bumbis.rpsim.core.simconfig;

import java.util.HashMap;
import java.util.Map;

public class SimConfig extends ConfigElement {

	//Simulation Objects
	private Map<String, Distribution> dists = new HashMap<String, Distribution>();
	private Map<String, ServiceProcessor> svcProcs = new HashMap<String, ServiceProcessor>();
	private Map<String, TokenGenerator> tokenGens = new HashMap<String, TokenGenerator>();
	private Map<String, Activity> activities = new HashMap<String, Activity>();
		 

	@SuppressWarnings("serial")
	private Map<Class<? extends ConfigElement>, HashMap<String, ? extends ConfigElement>> mapping = new HashMap<Class<? extends ConfigElement>, HashMap<String, ? extends ConfigElement>>() {
		{
			put(Distribution.class, (HashMap<String, Distribution>) dists);
			put(ServiceProcessor.class, (HashMap<String, ServiceProcessor>) svcProcs);
			put(TokenGenerator.class, (HashMap<String, TokenGenerator>) tokenGens);
			put(Activity.class, (HashMap<String, Activity>) activities);
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

	public Map<String, TokenGenerator> getTokenGens() {
		return tokenGens;
	}

	public Map<String, Activity> getActivities() {
		return activities;
	}
}