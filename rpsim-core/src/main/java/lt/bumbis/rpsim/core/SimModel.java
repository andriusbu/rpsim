package lt.bumbis.rpsim.core;

import java.util.HashMap;
import java.util.Observer;

import lt.bumbis.rpsim.core.elements.Distribution;
import lt.bumbis.rpsim.core.elements.ServiceProcessor;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;

public class SimModel extends Model {
	
	//Model elements
	private HashMap<String, Distribution> distributions;
	private HashMap<String, ServiceProcessor> svcProcessors;
	private HashMap<String, TokenGenerator> tokenGenerators;
	
	//Simulation engine elements
	
	//Other
	private IProcessEngine procEngine;
	private Observer timeSyncHandler;

	public SimModel(Model model, String name, boolean showInReport, boolean showInTrace) {
		super(model, name, showInReport, showInTrace);
		distributions = new HashMap<String, Distribution>();
		svcProcessors = new HashMap<String, ServiceProcessor>();
		tokenGenerators = new HashMap<String, TokenGenerator>();
	}

	@Override
	public String description() {
		return "Process simulation model using process engine";
	}

	@Override
	public void doInitialSchedules() {
		getExperiment().getSimClock().addObserver(timeSyncHandler);
		for (TokenGenerator tokenGen: tokenGenerators.values()) tokenGen.doInitialSchedules(this);
	}

	// Instantiate all static model components like queues and distributions
	@Override
	public void init() {
		for (Distribution dist : distributions.values()) dist.init(this);
		for (ServiceProcessor svcProc : svcProcessors.values()) svcProc.init(this);
		for (TokenGenerator tokenGen: tokenGenerators.values()) tokenGen.init(this);
//		procEngine.startEngine();
	}
	
	public ContDist getDist(String name) {
		return distributions.get(name).getDist();
	}

	public HashMap<String, Distribution> getDistributions() {
		return distributions;
	}

	public SimModel setDistributions(HashMap<String, Distribution> distributions) {
		this.distributions = distributions;
		return this;
	}

	public HashMap<String, ServiceProcessor> getSvcProcessors() {
		return svcProcessors;
	}

	public SimModel setSvcProcessors(HashMap<String, ServiceProcessor> svcProcessors) {
		this.svcProcessors = svcProcessors;
		return this;
	}

	public HashMap<String, TokenGenerator> getTokenGenerators() {
		return tokenGenerators;
	}

	public SimModel setTokenGenerators(HashMap<String, TokenGenerator> tokenGenerators) {
		this.tokenGenerators = tokenGenerators;
		return this;
	}

	public IProcessEngine getProcEngine() {
		return procEngine;
	}

	public SimModel setProcEngine(IProcessEngine procEngine) {
		this.procEngine = procEngine;
		return this;
	}
	
	public SimModel setTimeSyncHandler(Observer timeSyncHandler) {
		this.timeSyncHandler = timeSyncHandler;
		return this;
	}

}
