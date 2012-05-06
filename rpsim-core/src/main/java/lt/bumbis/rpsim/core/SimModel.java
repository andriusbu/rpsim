package lt.bumbis.rpsim.core;

import java.util.HashMap;
import java.util.Map;

import lt.bumbis.rpsim.core.entities.SvcProcessor;
import lt.bumbis.rpsim.core.events.NewProcessToken;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;

public class SimModel extends Model {
	
	private SimConfig config;
	private Map<String, ContDist> dists = new HashMap<String, ContDist>();
	private Map<String, NewProcessToken> tokenGens = new HashMap<String, NewProcessToken>();
	private Map<String, SvcProcessor> svcProcessors = new HashMap<String, SvcProcessor>();
	
	private HashMap<String, SvcProcessor> activityMapping = new HashMap<String, SvcProcessor>();
	
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
		ModelBuilder.doInitialSchedules(this);
	}

	@Override
	public void init() {
		ModelBuilder.init(this);		
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
	
	public Map<String, NewProcessToken> getTokenGenerators() {
		return tokenGens;
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

	public SvcProcessor getActivity(String name) {
		return activityMapping.get(name);
	}

	public void addActivity(String name, SvcProcessor processor) {
		this.activityMapping.put(name, processor);
	}

	public SvcProcessor getSvcProcessor(String name) {
		return svcProcessors.get(name);
	}

	public void addSvcProcessor(String name, SvcProcessor processor) {
		this.svcProcessors.put(name, processor);
	}
}
