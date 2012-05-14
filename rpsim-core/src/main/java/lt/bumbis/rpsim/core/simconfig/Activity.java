package lt.bumbis.rpsim.core.simconfig;

public class Activity extends ConfigElement<Activity> {
	
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
