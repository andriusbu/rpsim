package lt.bumbis.rpsim.core.entities;

import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class SvcReq extends Entity {
	
	private SvcProcessor svcProcessor;
	//TODO add handler

	public SvcReq(Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);
	}
	

}
