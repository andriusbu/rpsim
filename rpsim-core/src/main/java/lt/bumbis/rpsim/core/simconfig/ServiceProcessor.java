package lt.bumbis.rpsim.core.simconfig;

import java.util.concurrent.TimeUnit;

public class ServiceProcessor extends ConfigElement<ServiceProcessor> {
	
	private int numExec;
	private String distName;
	private TimeUnit timeUnit;

	//---------------------------------------------
	// Constructors
	//---------------------------------------------
	public ServiceProcessor() {
		super();
		this.numExec = Integer.MAX_VALUE;
		this.timeUnit = TimeUnit.HOURS;
	}
	
	public ServiceProcessor(String name, int numExec,
			String distName, TimeUnit timeUnit, boolean showInReport,
			boolean showInTrace) {
		super(name, showInReport, showInTrace);
		this.numExec = numExec;
		this.distName = distName;
		this.timeUnit = timeUnit;
	}

	//---------------------------------------------
	// Configuration methods
	//---------------------------------------------
	public ServiceProcessor numExec(int numExec) {
		setNumExec(numExec);
		return this;
	}
	
	public ServiceProcessor dist(String dist) {
		setDistName(dist);
		return this;
	}
	
	public ServiceProcessor timeUnit(TimeUnit timeUnit) {
		setTimeUnit(timeUnit);
		return this;
	}
	
	//---------------------------------------------
	// Getter and Setters
	//---------------------------------------------	
	public int getNumExec() {
		return numExec;
	}

	public void setNumExec(int numExec) {
		this.numExec = numExec;
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
