package lt.bumbis.rpsim.core.entities;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;

public class ResPool extends Entity {
	
	private Queue<ResEntity> resQueue;
	private Queue<SvcReq> svcRequestWaitQueue;

	public ResPool(Model model, String name, long resCount, boolean showInReport, boolean showInTrace) {
		super(model, name, showInTrace);
		resQueue = new Queue<ResEntity>(model, name+"_RQ", showInReport, showInTrace);
		for (int i=0; i<resCount; i++) {
			ResEntity re = new ResEntity(model, name+"ResEntity", showInTrace);
			resQueue.insert(re);
		}
		svcRequestWaitQueue = new Queue<SvcReq>(model, name+"_WQ", showInReport, showInTrace);
	}

	public Queue<ResEntity> getResQueue() {
		return resQueue;
	}

	public Queue<SvcReq> getSvcRequestWaitQueue() {
		return svcRequestWaitQueue;
	}
}
