package lt.bumbis.rpsim.core.entities;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.statistic.Tally;

public class ProcessContainer extends Entity {
	
	private Tally execTime;
	private Queue<Process> activeProcessQueue;

	public ProcessContainer(Model model, String name, boolean showInReport) {
		super(model, name, showInReport);
		execTime = new Tally(model, "ProcExecTime_"+name, showInReport, showInReport);
		activeProcessQueue = new Queue<Process>(model, "ActiveProcessQueue_"+name, showInReport, showInReport);		
	}
	
	public void startProcess(Process proc) {
		proc.setStartTime(presentTime().getTimeAsDouble());
		activeProcessQueue.insert(proc);
	}
	
	public void completeProcess(Process proc) {
		activeProcessQueue.remove(proc);
		proc.setCompletionTime(presentTime().getTimeAsDouble());
		execTime.update(proc.getCompletionTime() - proc.getStartTime());
	}

	public Tally getExecTime() {
		return execTime;
	}

	public Queue<Process> getActiveProcessQueue() {
		return activeProcessQueue;
	}	
}
