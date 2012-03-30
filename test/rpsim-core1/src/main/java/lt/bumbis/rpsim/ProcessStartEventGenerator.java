package lt.bumbis.rpsim;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;

public class ProcessStartEventGenerator extends ExternalEvent {

	public ProcessStartEventGenerator(Model owner, String name, boolean showInTrace) {
		super(owner, name, showInTrace);
		
	}

	@Override
	public void eventRoutine() {
		ProcessSimulationModel model = (ProcessSimulationModel)getModel();
		
		
	}

}
