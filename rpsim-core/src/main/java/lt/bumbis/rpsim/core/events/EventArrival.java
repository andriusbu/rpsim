package lt.bumbis.rpsim.core.events;

import lt.bumbis.rpsim.core.entities.ProcessEvent;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;

public class EventArrival extends Event<ProcessEvent> {

	public EventArrival(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	@Override
	public void eventRoutine(ProcessEvent arg0) {

		
	}



}
