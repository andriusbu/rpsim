package lt.bumbis.rpsim.core.entities;

import lt.bumbis.rpsim.core.IHandler;
import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class SvcReq extends Entity {
	
	private IHandler handler;

	public SvcReq(IHandler handler, Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);
        this.handler = handler;        
	}

	public IHandler getHandler() {
		return handler;
	}

	public void setHandler(IHandler handler) {
		this.handler = handler;
	}
}
