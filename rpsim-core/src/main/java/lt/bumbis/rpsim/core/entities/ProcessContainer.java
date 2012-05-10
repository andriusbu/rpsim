package lt.bumbis.rpsim.core.entities;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.statistic.Tally;

public class ProcessContainer extends Entity {
	
	private Tally execTime;

	public ProcessContainer(Model model, String name, boolean showInReport) {
		super(model, name, showInReport);
		execTime = new Tally(model, "ProcExecTime_"+name, showInReport, showInReport);
	}
	
	public void startProcess(Process proc) {
		proc.setStartTime(presentTime().getTimeAsDouble());
	}
	
	public void completeProcess(Process proc) {
		proc.setCompletionTime(presentTime().getTimeAsDouble());
		execTime.update(proc.getCompletionTime() - proc.getStartTime());
	}

	public Tally getExecTime() {
		return execTime;
	}
}
