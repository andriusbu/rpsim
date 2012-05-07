package lt.bumbis.rpsim.core;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.SimClock;

public class SimClockObserver implements Observer {
	
	private TimeUnit timeUnit;
	private IProcessEngine procEngine;
	
	public SimClockObserver(TimeUnit timeUnit, IProcessEngine procEngine) {
		this.timeUnit = timeUnit;
		this.procEngine = procEngine;
	}

	public void update(Observable o, Object arg) {
		long time = ((SimClock)o).getTime().getTimeRounded(timeUnit);
		procEngine.syncTime(time, timeUnit);
	}	
}
