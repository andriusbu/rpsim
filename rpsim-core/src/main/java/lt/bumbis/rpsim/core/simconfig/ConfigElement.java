package lt.bumbis.rpsim.core.simconfig;

public abstract class ConfigElement {
	
	private String name;
	private boolean showInReport;
	private boolean showInTrace;
	
	
	//---------------------------------------------
	// Constructors
	//---------------------------------------------
	public ConfigElement() {
		showInReport=true;
		showInTrace=true;
	}
	
	public ConfigElement(String name, boolean showInReport, boolean showInTrace) {
		this.name = name;
		this.showInReport = showInReport;
		this.showInTrace = showInTrace;
	}
	
	//---------------------------------------------
	// Configuration methods
	//---------------------------------------------
	public abstract ConfigElement name(String name);
	
	public abstract ConfigElement showInReport(boolean showInReport);
	
	public abstract ConfigElement showInTrace(boolean showInTrace);
	
	//---------------------------------------------
	// Getter and Setters
	//---------------------------------------------
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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