package lt.bumbis.rpsim.core.simconfig;

import java.util.concurrent.TimeUnit;

public class TokenGenerator extends ConfigElement<TokenGenerator> {
		
	private String processName;
    private String distName;
    private TimeUnit timeUnit;
    private String dataProvider;
    private boolean useContextData = false;

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
	
	public TokenGenerator dataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
		return this;
	}
	
	public TokenGenerator useContextData(boolean value) {
		this.useContextData = value;
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

	public String getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}

	public boolean isUseContextData() {
		return useContextData;
	}

	public void setUseContextData(boolean useContextData) {
		this.useContextData = useContextData;
	}
}
