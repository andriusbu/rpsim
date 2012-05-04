package lt.bumbis.rpsim.core.simconfig;

import java.util.HashMap;

public class SimConfig extends ConfigElement {

	private String name;

	private HashMap<String, Distribution> dists = new HashMap<String, Distribution>();
	private HashMap<String, ServiceProcessor> svcProcs = new HashMap<String, ServiceProcessor>();
	private HashMap<String, TokenGenerator> tokenGens = new HashMap<String, TokenGenerator>();

	@SuppressWarnings("serial")
	private HashMap<Class<? extends ConfigElement>, HashMap<String, ? extends ConfigElement>> mapping = new HashMap<Class<? extends ConfigElement>, HashMap<String, ? extends ConfigElement>>() {
		{
			put(Distribution.class, dists);
			put(ServiceProcessor.class, svcProcs);
			put(TokenGenerator.class, tokenGens);
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

	public String getName() {
		return name;
	}

	public HashMap<String, Distribution> getDists() {
		return dists;
	}

	public HashMap<String, ServiceProcessor> getSvcProcs() {
		return svcProcs;
	}

	public HashMap<String, TokenGenerator> getTokenGens() {
		return tokenGens;
	}
}