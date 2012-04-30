package lt.bumbis.rpsim.core;

public abstract class SimModelElement {
	
	public abstract void init(SimModel model);
	public abstract void doInitialSchedules(SimModel model);
	
}
