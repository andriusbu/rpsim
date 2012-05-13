package lt.bumbis.rpsim.core.simconfig;

import java.util.concurrent.TimeUnit;

public class TokenGenerator extends ConfigElement {
		
	private String processName;
    private String distName;
    private TimeUnit timeUnit;

	//---------------------------------------------
	// Constructors
	//---------------------------------------------
    public TokenGenerator() {
    	super();
    }
    
	public TokenGenerator(String name, String processName,
			String distName, TimeUnit timeUnit, boolean showInReport, boolean showInTrace) {
		super(name, showInReport, showInTrace);
		this.processName = processName;
		this.distName = distName;
		this.timeUnit = timeUnit;
	}
	
	//---------------------------------------------
	// Configuration methods
	//---------------------------------------------
	@Override
	public TokenGenerator name(String name) {
		setName(name);
		return this;
	}
	
	@Override
	public TokenGenerator showInReport(boolean showInReport) {
		setShowInReport(showInReport);
		return this;
	}
	
	@Override
	public TokenGenerator showInTrace(boolean showInTrace) {
		setShowInTrace(showInTrace);
		return this;
	}
	
	public TokenGenerator process(String process) {
		setProcessName(process);
		return this;
	}
	
	public TokenGenerator dist(String dist) {
		setDistName(dist);
		return this;
	}
	
	public TokenGenerator timeUnit(TimeUnit timeUnit) {
		setTimeUnit(timeUnit);
		return this;
	}
	
	//---------------------------------------------
	// Getter and Setters
	//---------------------------------------------
	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
}
