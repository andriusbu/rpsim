package lt.bumbis.rpsim.core;

import java.util.HashMap;
import java.util.Map;

import lt.bumbis.rpsim.core.entities.SvcReqExec;
import lt.bumbis.rpsim.core.entities.SvcReq;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;

public class SimModel extends Model {
	
	private HashMap<String, Distribution> distributions;
	private HashMap<String, ServiceProcessor> svcProcessors;
	private HashMap<String, TokenGenerator> tokenGenerators;

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
		for (TokenGenerator tokenGen: tokenGenerators.values()) tokenGen.doInitialSchedules();
	}

	// Instantiate all static model components like queues and distributions
	@Override
	public void init() {
		for (Distribution dist : distributions.values()) dist.init(this);
		for (ServiceProcessor svcProc : svcProcessors.values()) svcProc.init(this);
		for (TokenGenerator tokenGen: tokenGenerators.values()) tokenGen.init(this);
	}
	
	public HashMap<String, Distribution> getDists() {
		return distributions;
	}
	
	public ContDist getDist(String name) {
		return distributions.get(name).getDist();
	}

}
