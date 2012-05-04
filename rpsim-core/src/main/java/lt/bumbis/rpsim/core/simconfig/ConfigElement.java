package lt.bumbis.rpsim.core.simconfig;

public abstract class ConfigElement {
	
	private String name;
	private boolean showInReport;
	private boolean showInTrace;
	
	public ConfigElement(String name, boolean showInReport, boolean showInTrace) {
		this.name = name;
		this.showInReport = showInReport;
		this.showInTrace = showInTrace;
	}
	
	public String getName() {
		return name;
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