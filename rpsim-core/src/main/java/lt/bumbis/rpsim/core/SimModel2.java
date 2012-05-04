package lt.bumbis.rpsim.core;

import java.util.HashMap;

import lt.bumbis.rpsim.core.simconfig.SimConfig;
import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;

public class SimModel2 extends Model {
	
	private SimConfig config;
	private HashMap<String, ContDist> dists;

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
	
	public void addDist(String name, ContDist dist) {
		this.dists.put(name, dist);
	}
	
	public ContDist getDist(String name) {
		return dists.get(name);
	}
}
