package lt.bumbis.rpsim.core;

import java.util.HashMap;
import java.util.Map;

import lt.bumbis.rpsim.core.entities.SvcReqExec;
import lt.bumbis.rpsim.core.entities.SvcReq;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;

public class SimModel extends Model {
	
	private HashMap<String, ServiceProcessor> svcProcessors;

	public SimModel(Model model, String name, boolean showInReport, boolean showInTrace) {
		super(model, name, showInReport, showInTrace);
		svcProcessors = new HashMap<String, ServiceProcessor>();
	}

	@Override
	public String description() {
		return "Process simulation model using process engine";
	}

	@Override
	public void doInitialSchedules() {
		// TODO Auto-generated method stub
	}

	// Instantiate all static model components like queues and distributions
	@Override
	public void init() {
		for (ServiceProcessor svcProc : svcProcessors.values()) {
			svcProc.init(this);
		}			
	}

}
