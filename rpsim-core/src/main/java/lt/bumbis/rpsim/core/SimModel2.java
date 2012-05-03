package lt.bumbis.rpsim.core;

import lt.bumbis.rpsim.core.simconfig.SimConfig;
import desmoj.core.simulator.Model;

public class SimModel2 extends Model {
	
	private SimConfig config;

	public SimModel2(SimConfig config) {
		super(null, config.getName(), config.isShowInReport(), config.isShowInTrace());
		this.config = config;
	}

	@Override
	public String description() {
		// TODO Add description
		return "Description...";
	}

	@Override
	public void doInitialSchedules() {
		ModelBuilder.doInitialSchedules(this, config);
	}

	@Override
	public void init() {
		ModelBuilder.init(this, config);		
	}
	

}
