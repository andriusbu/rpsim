package lt.bumbis.rpsim.core.entities;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class Process extends Entity {
	
	private double startTime;
	private double completionTime;

	public Process(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(double completionTime) {
		this.completionTime = completionTime;
	}

}
