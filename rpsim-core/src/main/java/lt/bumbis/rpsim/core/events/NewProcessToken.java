package lt.bumbis.rpsim.core.events;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;

public class NewProcessToken extends ExternalEvent {

	public NewProcessToken(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	@Override
	public void eventRoutine() {
		//TODO
	}

}
