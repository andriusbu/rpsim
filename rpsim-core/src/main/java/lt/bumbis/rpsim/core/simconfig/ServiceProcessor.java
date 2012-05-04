package lt.bumbis.rpsim.core.simconfig;

import java.util.concurrent.TimeUnit;

public class ServiceProcessor extends ConfigElement {
	
	private int numExec;
	private String distName;
	private TimeUnit timeUnit;

	public ServiceProcessor(String name, int numExec,
			String distName, TimeUnit timeUnit, boolean showInReport,
			boolean showInTrace) {
		super(name, showInReport, showInTrace);
		this.numExec = numExec;
		this.distName = distName;
		this.timeUnit = timeUnit;
	}

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
