package lt.bumbis.rpsim.core.entities;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class SvcProcessorExec extends Entity {
	
	private SvcReq svcReq;

	public SvcProcessorExec(Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);
    }

	public SvcReq getSvcReq() {
		return svcReq;
	}

	public void setSvcReq(SvcReq svcReq) {
		this.svcReq = svcReq;
	}
}
