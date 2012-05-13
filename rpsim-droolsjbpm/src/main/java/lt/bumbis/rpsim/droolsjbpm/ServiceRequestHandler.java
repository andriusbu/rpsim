package lt.bumbis.rpsim.droolsjbpm;

import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

import lt.bumbis.rpsim.core.IServiceHandler;

public class ServiceRequestHandler implements IServiceHandler {
	
	private WorkItem workItem;
	private WorkItemManager workItemManager;
	
	public ServiceRequestHandler(WorkItem workItem,	WorkItemManager workItemManager) {
		this.workItem = workItem;
		this.workItemManager = workItemManager;
	}

	public void update() {
		workItemManager.completeWorkItem(workItem.getId(), null);
	}

}
