package lt.bumbis.rpsim.core.entities;

import java.util.concurrent.TimeUnit;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;

public class SvcProcessor extends Entity {
	
	private Queue<SvcReq> waitQueue;
	private Queue<SvcProcessorExec> idleQueue;
	private ContDist serviceTimeDist;
	private TimeUnit serviceTimeUnit;

	public SvcProcessor(Model model, String name, boolean showInReport) {
		super(model, name, showInReport);
	}
	
	public SvcProcessor set(
				Queue<SvcReq> waitQueue,
				Queue<SvcProcessorExec> idleQueue,
				ContDist serviceTimeDist,
				TimeUnit serviceTimeUnit) {
		this.waitQueue = waitQueue;
		this.idleQueue = idleQueue;
		this.serviceTimeDist = serviceTimeDist;
		this.serviceTimeUnit = serviceTimeUnit;
		return this;
	}

	public Queue<SvcReq> getWaitQueue() {
		return waitQueue;
	}

	public SvcProcessor setWaitQueue(Queue<SvcReq> waitQueue) {
		this.waitQueue = waitQueue;
		return this;
	}

	public Queue<SvcProcessorExec> getIdleQueue() {
		return idleQueue;
	}

	public SvcProcessor setIdleQueue(Queue<SvcProcessorExec> idleQueue) {
		this.idleQueue = idleQueue;
		return this;
	}

	public ContDist getServiceTimeDist() {
		return serviceTimeDist;
	}

	public SvcProcessor setServiceTimeDist(ContDist serviceTimeDist) {
		this.serviceTimeDist = serviceTimeDist;
		return this;
	}

	public TimeUnit getServiceTimeUnit() {
		return serviceTimeUnit;
	}

	public SvcProcessor setServiceTimeUnit(TimeUnit serviceTimeUnit) {
		this.serviceTimeUnit = serviceTimeUnit;
		return this;
	}
}
