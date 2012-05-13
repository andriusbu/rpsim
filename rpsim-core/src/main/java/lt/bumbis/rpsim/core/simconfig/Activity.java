package lt.bumbis.rpsim.core.simconfig;

public class Activity extends ConfigElement {
	
	private String svcProcessor;
		
	//---------------------------------------------
	// Constructors
	//---------------------------------------------
	public Activity() {
		super();
	}
	
	public Activity(String name, String svcProcessor) {
		super(name, false, false);
		this.svcProcessor = svcProcessor;
	}
	
	//---------------------------------------------
	// Configuration methods
	//---------------------------------------------
	@Override
	public Activity name(String name) {
		setName(name);
		return this;
	}

	@Override
	public Activity showInReport(boolean showInReport) {
		setShowInReport(showInReport);
		return this;
	}

	@Override
	public Activity showInTrace(boolean showInTrace) {
		setShowInTrace(showInTrace);
		return this;
	}
	
	public Activity svcProcessor(String svcProcessor) {
		setSvcProcessor(svcProcessor);
		return this;
	}

	//---------------------------------------------
	// Getter and Setters
	//---------------------------------------------
	public String getSvcProcessor() {
		return svcProcessor;
	}

	public void setSvcProcessor(String svcProcessor) {
		this.svcProcessor = svcProcessor;
	}
}
