package lt.bumbis.rpsim.core.events;

import lt.bumbis.rpsim.core.entities.ProcessContainer;
import lt.bumbis.rpsim.core.entities.Process;
import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;

@Deprecated
public class ProcessArrival extends EventOf2Entities<ProcessContainer, Process> {

	public ProcessArrival(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	@Override
	public void eventRoutine(ProcessContainer container, Process process) {
		container.startProcess(process);
	}

}