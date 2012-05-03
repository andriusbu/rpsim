package lt.bumbis.rpsim.core.simconfig;

import java.util.HashMap;

public class SimConfig extends ConfigElement {

	private String name;
	private boolean showInTrace;
	private boolean showInReport;
	
	private HashMap<String, Distribution> dists = new HashMap<String, Distribution>();
	private HashMap<String, ServiceProcessor> svcProcs = new HashMap<String, ServiceProcessor>();
	private HashMap<String, TokenGenerator> tokenGens = new HashMap<String, TokenGenerator>();
		
	// TODO Check implementation
	@SuppressWarnings("serial")
	private HashMap<Class<? extends ConfigElement>, HashMap<String, ? extends ConfigElement>> mapping = new HashMap<Class<? extends ConfigElement>, HashMap<String, ? extends ConfigElement>>() {{
		put(Distribution.class, dists);
		put(ServiceProcessor.class, svcProcs);
		put(TokenGenerator.class, tokenGens);
	}};
	
	public SimConfig(String name) {
		super(name);
		this.showInTrace = false;
		this.showInReport = false;
	}
	
	public SimConfig(String name, boolean showInTrace, boolean showInReport) {
		super(name);
		this.showInTrace = showInTrace;
		this.showInReport = showInReport;
	}

	// TODO Check implementation
	@SuppressWarnings("unchecked")
	public <T extends ConfigElement> SimConfig add(T element) {
		HashMap<String, T> col = (HashMap<String, T>) mapping.get(element.getClass());
		col.put(element.getName(), element);
		return this;
	}
	
	public HashMap<String, ? extends ConfigElement> get(Class<? extends ConfigElement> clazz) {
		return mapping.get(clazz);
	}

	public boolean isShowInTrace() {
		return showInTrace;
	}

	public void setShowInTrace(boolean showInTrace) {
		this.showInTrace = showInTrace;
	}

	public boolean isShowInReport() {
		return showInReport;
	}

	public void setShowInReport(boolean showInReport) {
		this.showInReport = showInReport;
	}

	public String getName() {
		return name;
	}
}
