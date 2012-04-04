package lt.bumbis.rpsim.core.entities;

import java.util.concurrent.TimeUnit;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;

public class ServiceRequest extends Entity {
	
	private Object externalRef;
	private Queue<ServiceRequest> waitQueue;
	private Queue<ServiceProcessor> procIdleQueue;
	private TimeUnit timeUnit;
	private ContDist dist;
	private ServiceProcessor serviceProcessor;

	public ServiceRequest(Model model, String name, boolean showInTrace) {
        super(model, name, showInTrace);
    }
    
    public ServiceRequest setExternalRef(Object externalRef) {
    	this.externalRef = externalRef;
    	return this;
    }
    
    public ServiceRequest setWaitQueue(Queue<ServiceRequest> waitQueue) {
    	this.waitQueue = waitQueue;
    	return this;
    }
    
    public ServiceRequest setProcIdleQueue(Queue<ServiceProcessor> procIdleQueue) {
    	this.procIdleQueue = procIdleQueue;
    	return this;
    }
    
	public Object getExternalRef() {
		return externalRef;
	}

	public Queue<ServiceRequest> getWaitQueue() {
		return waitQueue;
	}

	public Queue<ServiceProcessor> getProcIdleQueue() {
		return procIdleQueue;
	}
	
    public ServiceProcessor getServiceProcessor() {
		return serviceProcessor;
	}

	public ServiceRequest setServiceProcessor(ServiceProcessor serviceProcessor) {
		this.serviceProcessor = serviceProcessor;
		return this;
	}

    public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public ServiceRequest setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
		return this;
	}

	public ContDist getDist() {
		return dist;
	}

	public ServiceRequest setDist(ContDist dist) {
		this.dist = dist;
		return this;
	}

	public ServiceRequest setParameters(Queue<ServiceRequest> waitQueue, Queue<ServiceProcessor> procIdleQueue, ContDist dist, TimeUnit timeUnit, Object externalRef) {
    	setExternalRef(externalRef);
    	setWaitQueue(waitQueue);
    	setProcIdleQueue(procIdleQueue);
    	setTimeUnit(timeUnit);
    	setDist(dist);
    	return this;
    }
}
