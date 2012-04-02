package lt.bumbis.rpsim.core;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class ServiceRequest extends Entity {
	
	private long processInstanceId;
	private String activityId;

	public ServiceRequest(Model arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public ServiceRequest setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
		return this;
	}

	public String getActivityId() {
		return activityId;
	}

	public ServiceRequest setActivityId(String activityId) {
		this.activityId = activityId;
		return this;
	}

}
