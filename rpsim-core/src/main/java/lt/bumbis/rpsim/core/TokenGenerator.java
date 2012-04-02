package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

@objid ("b33ac24a-7a89-11e1-9a4b-028037ec0200")
public class TokenGenerator extends ExternalEvent {
	
	private String processName;
	private ContDist dist;
	private TimeUnit timeUnit;

    @objid ("7d7a3c92-7a8b-11e1-9a4b-028037ec0200")
    public TokenGenerator(final Model arg0, final String arg1, final boolean arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    @objid ("7d7a3c97-7a8b-11e1-9a4b-028037ec0200")
    public void eventRoutine() {
        SimulationEngine simEngine = (SimulationEngine)getModel();
        simEngine.syncTime();
        simEngine.getProcessEngine().startProcess(processName);
        schedule(new TimeSpan(dist.sample(), timeUnit));
    }
    
    public TokenGenerator setProcessName(String processName) {
    	this.processName = processName;
    	return this;
    }
    
    public TokenGenerator setDistribution(ContDist dist) {
    	this.dist = dist;
    	return this;
    }
    
    public TokenGenerator setTimeUnit(TimeUnit timeUnit) {
    	this.timeUnit = timeUnit;
    	return this;
    }
    
    public TokenGenerator settings(String processName, ContDist dist, TimeUnit timeUnit) {
    	setProcessName(processName);
    	setDistribution(dist);
    	setTimeUnit(timeUnit);
    	return this;
    }

}
