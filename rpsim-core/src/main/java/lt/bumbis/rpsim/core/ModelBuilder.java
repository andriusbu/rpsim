package lt.bumbis.rpsim.core;

public abstract class ModelBuilder {
	
	@SuppressWarnings("unused")
	private SimulationEngine simEngine;
	
	public ModelBuilder(SimulationEngine simEngine) {
		this.simEngine = simEngine;
	}
	
	abstract public void init();
	abstract public void doInitialSchedules();

}
