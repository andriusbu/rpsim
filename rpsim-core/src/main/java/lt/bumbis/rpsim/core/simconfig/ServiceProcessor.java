package lt.bumbis.rpsim.core.simconfig;

import java.util.concurrent.TimeUnit;

public class ServiceProcessor extends ConfigElement {
	
	private int numExec;
	private String distName;
	private TimeUnit timeUnit;
	private boolean showInReport;
	private boolean showInTrace;

	public ServiceProcessor(String name) {
		super(name);
	}

	public ServiceProcessor(String name, int numExec,
			String distName, TimeUnit timeUnit, boolean showInReport,
			boolean showInTrace) {
		super(name);
		settigns(numExec, distName, timeUnit, showInReport, showInTrace);
	}

	public ServiceProcessor settigns(int numExec,
			String distName, TimeUnit timeUnit, boolean showInReport,
			boolean showInTrace) {
		this.numExec = numExec;
		this.distName = distName;
		this.timeUnit = timeUnit;
		this.showInReport = showInReport;
		this.showInTrace = showInTrace;
		return this;
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

	public boolean isShowInReport() {
		return showInReport;
	}

	public void setShowInReport(boolean showInReport) {
		this.showInReport = showInReport;
	}

	public boolean isShowInTrace() {
		return showInTrace;
	}

	public void setShowInTrace(boolean showInTrace) {
		this.showInTrace = showInTrace;
	}
}
