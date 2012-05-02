package lt.bumbis.rpsim.core.elements;

import lt.bumbis.rpsim.core.SimModel;

public abstract class SimModelElement {
	
	public abstract void init(SimModel model);
	public abstract void doInitialSchedules(SimModel model);
	
}
