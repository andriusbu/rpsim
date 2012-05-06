package lt.bumbis.rpsim.core.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.TimeSpan;

public class SvcProcessor extends Entity {
	
	private Queue<SvcReq> waitQueue;
	private Queue<SvcProcessorExec> idleQueue;
	private Map<SvcReq, SvcProcessorExec> map;
	private ContDist serviceTimeDist;
	private TimeUnit serviceTimeUnit;

	public SvcProcessor(Model model, String name, boolean showInReport) {
		super(model, name, showInReport);
		map = new HashMap<SvcReq, SvcProcessorExec>();
	}
	
	public void add(SvcReq request) {
		waitQueue.insert(request);
	}
	
	public boolean isAvailable() {
		return ! idleQueue.isEmpty();
	}
	
	public boolean haveRequest() {
		return ! waitQueue.isEmpty();
	}
	
	public TimeSpan start(SvcReq request) {
    	SvcProcessorExec reqExec = idleQueue.first();
    	idleQueue.remove(reqExec);
    	waitQueue.remove(request);
    	map.put(request, reqExec);
    	return getServiceTime();
	}
	
	public SvcReq getNextRequest() {
		if (waitQueue.isEmpty()) {
			return null;
		} else {
			return waitQueue.first();
		}
	}
	
	public void complete(SvcReq request) {
		idleQueue.insert(map.get(request));
		map.remove(request);
	}

	public Queue<SvcReq> getWaitQueue() {
		return waitQueue;
	}

	public void setWaitQueue(Queue<SvcReq> waitQueue) {
		this.waitQueue = waitQueue;
	}

	public Queue<SvcProcessorExec> getIdleQueue() {
		return idleQueue;
	}

	public void setIdleQueue(Queue<SvcProcessorExec> idleQueue) {
		this.idleQueue = idleQueue;
	}

	public ContDist getServiceTimeDist() {
		return serviceTimeDist;
	}

	public void setServiceTimeDist(ContDist serviceTimeDist) {
		this.serviceTimeDist = serviceTimeDist;
	}

	public TimeUnit getServiceTimeUnit() {
		return serviceTimeUnit;
	}

	public void setServiceTimeUnit(TimeUnit serviceTimeUnit) {
		this.serviceTimeUnit = serviceTimeUnit;
	}
	
	private TimeSpan getServiceTime() {
		return new TimeSpan(serviceTimeDist.sample(), serviceTimeUnit);
	}
}
