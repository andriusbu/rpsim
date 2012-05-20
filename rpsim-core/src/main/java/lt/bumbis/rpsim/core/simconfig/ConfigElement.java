package lt.bumbis.rpsim.core.simconfig;

@SuppressWarnings("rawtypes")
public abstract class ConfigElement<T extends ConfigElement> {
	
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
	@SuppressWarnings("unchecked")
	public T name(String name) {
		this.name = name;
		return ((T)this);
	}
	
	/**
	 * Defines should this element be shown in report
	 * @param showInReport - default TRUE
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T showInReport(boolean showInReport) {
		this.showInReport = showInReport;
		return ((T)this);
	}
	
	/**
	 * Defines should this element be shown in trace
	 * @param showInTrace - default TRUE
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T showInTrace(boolean showInTrace) {
		this.showInTrace = showInTrace;
		return ((T)this);
	}
	
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