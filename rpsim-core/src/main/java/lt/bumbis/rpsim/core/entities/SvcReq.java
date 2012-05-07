package lt.bumbis.rpsim.core.entities;

import lt.bumbis.rpsim.core.IServiceHandler;
import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class SvcReq extends Entity {
	
	private IServiceHandler handler;

	public SvcReq(IServiceHandler handler, Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);
        this.handler = handler;        
	}

	public IServiceHandler getHandler() {
		return handler;
	}

	public void setHandler(IServiceHandler handler) {
		this.handler = handler;
	}
}
