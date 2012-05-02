package lt.bumbis.rpsim.core.entities;

import java.util.concurrent.TimeUnit;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;

public class SvcReq extends Entity {
	
	private Object externalRef;
	private Queue<SvcReq> waitQueue;
	private Queue<SvcProcessorExec> procIdleQueue;
	private TimeUnit timeUnit;
	private ContDist dist;
	private SvcProcessorExec serviceProcessor;

	public SvcReq(Model model, String name, boolean showInTrace) {
        super(model, name, showInTrace);
    }
    
    public SvcReq setExternalRef(Object externalRef) {
    	this.externalRef = externalRef;
    	return this;
    }
    
    public SvcReq setWaitQueue(Queue<SvcReq> waitQueue) {
    	this.waitQueue = waitQueue;
    	return this;
    }
    
    public SvcReq setProcIdleQueue(Queue<SvcProcessorExec> procIdleQueue) {
    	this.procIdleQueue = procIdleQueue;
    	return this;
    }
    
	public Object getExternalRef() {
		return externalRef;
	}

	public Queue<SvcReq> getWaitQueue() {
		return waitQueue;
	}

	public Queue<SvcProcessorExec> getProcIdleQueue() {
		return procIdleQueue;
	}
	
    public SvcProcessorExec getServiceProcessor() {
		return serviceProcessor;
	}

	public SvcReq setServiceProcessor(SvcProcessorExec serviceProcessor) {
		this.serviceProcessor = serviceProcessor;
		return this;
	}

    public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public SvcReq setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
		return this;
	}

	public ContDist getDist() {
		return dist;
	}

	public SvcReq setDist(ContDist dist) {
		this.dist = dist;
		return this;
	}

	public SvcReq setParameters(Queue<SvcReq> waitQueue, Queue<SvcProcessorExec> procIdleQueue, ContDist dist, TimeUnit timeUnit, Object externalRef) {
    	setExternalRef(externalRef);
    	setWaitQueue(waitQueue);
    	setProcIdleQueue(procIdleQueue);
    	setTimeUnit(timeUnit);
    	setDist(dist);
    	return this;
    }
}
