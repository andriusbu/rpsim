package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.events.NewProcessToken;

import desmoj.core.dist.ContDist;

public class TokenGenerator {

	private String name;
	private String processName;
    private String distName;
    private TimeUnit timeUnit;
    private boolean showInReport;
    
    private ContDist dist;
    private NewProcessToken token;
      
  	public TokenGenerator(String name, String processName, String distName,
			TimeUnit timeUnit, boolean showInReport) {
		this.name = name;
		this.processName = processName;
		this.distName = distName;
		this.timeUnit = timeUnit;
		this.showInReport = showInReport;
	}
	
	public void init(SimModel model) {
		dist = model.getDist(distName);
		token = new NewProcessToken(model, name, showInReport);
	}
	
	public void doInitialSchedules() {
		
	}
	
	public ContDist getDist() {
		return dist;
	}
}
