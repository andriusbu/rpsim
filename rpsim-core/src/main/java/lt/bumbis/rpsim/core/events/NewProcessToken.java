package lt.bumbis.rpsim.core.events;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeInstant;
import desmoj.core.simulator.TimeSpan;

public class NewProcessToken extends ExternalEvent {
	
	private String processName;
	private ContDist dist;
	private TimeUnit timeUnit;
	
	private SimModel model;
	
	public NewProcessToken(Model model, String name, boolean showInReport) {
		super(model, name, showInReport);
		this.model = (SimModel) model;
	}

	@Override
	public void eventRoutine() {
		model.getProcessEngine().startProcess(processName);
		schedule(new TimeSpan(dist.sample(), timeUnit));
	}
	
	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public ContDist getDist() {
		return dist;
	}

	public void setDist(ContDist dist) {
		this.dist = dist;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
}