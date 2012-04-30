package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.events.NewProcessToken;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.TimeSpan;

public class TokenGenerator extends SimModelElement {

	//TokenGenerator parameters
	private String name;
	private String processName;
    private String distName;
    private TimeUnit timeUnit;
    private boolean showInReport;
    
    //Internal variables
    private ContDist dist;
    private NewProcessToken token;
    
    /**
     * 
     * @param name - TokenGenerator name
     * @param processName - Process name (id) which is periodically started by this TokenGenerator
     * @param distName - Distribution name referencing to distribution object in a model
     * @param timeUnit - Time Unit (Mili Sec, Sec, Min, Hours)
     * @param showInReport
     */
    public TokenGenerator(String name, String processName, String distName,
			TimeUnit timeUnit, boolean showInReport) {
		this.name = name;
		this.processName = processName;
		this.distName = distName;
		this.timeUnit = timeUnit;
		this.showInReport = showInReport;
	}
	
	@Override
    public void init(SimModel model) {
		dist = model.getDist(distName);
		token = new NewProcessToken(model, name, showInReport);
		token.set(processName, dist, timeUnit);
	}

	@Override
	public void doInitialSchedules(SimModel model) {
		token.schedule(new TimeSpan(dist.sample(), timeUnit));		
	}
}
