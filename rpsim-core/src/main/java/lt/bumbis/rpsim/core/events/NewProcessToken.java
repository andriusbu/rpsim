package lt.bumbis.rpsim.core.events;

import java.util.concurrent.TimeUnit;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeInstant;

public class NewProcessToken extends ExternalEvent {
	
	private String processName;
	private ContDist dist;
	private TimeUnit timeUnit;
	
	public NewProcessToken(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	@Override
	public void eventRoutine() {
		//TODO Start process in process engine
		schedule(new TimeInstant(dist.sample(), timeUnit));
	}
	
	public NewProcessToken set(String processName, ContDist dist, TimeUnit timeUnit) {
		this.processName = processName;
		this.dist = dist;
		this.timeUnit = timeUnit;
		return this;
	}
}