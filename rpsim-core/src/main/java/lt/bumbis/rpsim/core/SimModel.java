package lt.bumbis.rpsim.core;

import java.util.HashMap;

import lt.bumbis.rpsim.core.events.NewProcessToken;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;

public class SimModel extends Model {
	
	private SimConfig config;
	private HashMap<String, ContDist> dists = new HashMap<String, ContDist>();
	private HashMap<String, NewProcessToken> tokenGens = new HashMap<String, NewProcessToken>();
	
	private IProcessEngine processEngine;

	public SimModel(SimConfig config) {
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
	
	public void addTokenGenerator(String name, NewProcessToken procToken) {
		tokenGens.put(name, procToken);
	}
	
	public NewProcessToken getTokenGenerator(String name) {
		return tokenGens.get(name);
	}

	public IProcessEngine getProcessEngine() {
		return processEngine;
	}

	public void setProcessEngine(IProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	public SimConfig getConfig() {
		return config;
	}
}
