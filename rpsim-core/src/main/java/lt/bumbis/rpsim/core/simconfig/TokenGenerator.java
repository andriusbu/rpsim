package lt.bumbis.rpsim.core.simconfig;

import java.util.concurrent.TimeUnit;

public class TokenGenerator extends ConfigElement {
		
	private String processName;
    private String distName;
    private TimeUnit timeUnit;

	public TokenGenerator(String name, String processName,
			String distName, TimeUnit timeUnit, boolean showInReport, boolean showInTrace) {
		super(name, showInReport, showInTrace);
		this.processName = processName;
		this.distName = distName;
		this.timeUnit = timeUnit;
	}
	
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
