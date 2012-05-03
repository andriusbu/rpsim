package lt.bumbis.rpsim.core.simconfig;

import java.util.concurrent.TimeUnit;

public class TokenGenerator extends ConfigElement {
		
	private String processName;
    private String distName;
    private TimeUnit timeUnit;
    private boolean showInReport;

	public TokenGenerator(String name) {
		super(name);
	}

	public TokenGenerator(String name, String processName,
			String distName, TimeUnit timeUnit, boolean showInReport) {
		super(name);
		settings(processName, distName, timeUnit, showInReport);
	}
	
	public TokenGenerator settings(String processName,
			String distName, TimeUnit timeUnit, boolean showInReport) {
		this.processName = processName;
		this.distName = distName;
		this.timeUnit = timeUnit;
		this.showInReport = showInReport;
		return this;
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

	public boolean isShowInReport() {
		return showInReport;
	}

	public void setShowInReport(boolean showInReport) {
		this.showInReport = showInReport;
	}
}
