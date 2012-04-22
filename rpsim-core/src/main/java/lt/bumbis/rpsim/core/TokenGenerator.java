package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;

import desmoj.core.dist.ContDist;

public class TokenGenerator {

	private String name;
	private String processName;
    private String distName;
    private TimeUnit timeUnit;
    
    private ContDist dist;
      
  	public TokenGenerator(String name, String processName, String distName,
			TimeUnit timeUnit) {
		this.name = name;
		this.processName = processName;
		this.distName = distName;
		this.timeUnit = timeUnit;
	}
	
	public void init(SimModel model) {
		dist = model.getDist(distName);
	}
	
	public void doInitialSchedules() {
		//TODO
	}
}
