package lt.bumbis.rpsim.core.events;

import lt.bumbis.rpsim.core.entities.ProcessContainer;
import lt.bumbis.rpsim.core.entities.Process;
import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;

public class ProcessCompletion extends EventOf2Entities<ProcessContainer, Process> {

	public ProcessCompletion(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	@Override
	public void eventRoutine(ProcessContainer container, Process process) {
		container.completeProcess(process);
	}

}