package lt.bumbis.rpsim.core.entities;

import lt.bumbis.rpsim.core.IServiceHandler;
import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class SvcReq extends Entity {
	
	private IServiceHandler handler;
	private double arrivalTime;
	private double startTime;
	private double completeTime;

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

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(double completeTime) {
		this.completeTime = completeTime;
	}
}
