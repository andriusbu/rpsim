package lt.bumbis.rpsim.droolsjbpm;

import lt.bumbis.rpsim.core.ISimEngine;

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomWorkItemHandler implements WorkItemHandler {
	
	final static Logger logger = LoggerFactory.getLogger(CustomWorkItemHandler.class);
	
	private ISimEngine simEngine;
	
	public CustomWorkItemHandler(ISimEngine simEngine) {
		this.simEngine = simEngine;
	}

	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		
	}
	
	protected void executeWorkItem(WorkItem workItem, String nodeId, WorkItemManager workItemManager) {
		simEngine.newServiceRequest(nodeId, new ServiceRequestHandler(workItem, workItemManager));
	}
	
	public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
		logger.debug("Executin work item", workItem.getName());
		executeWorkItem(workItem, workItem.getName(), workItemManager);		
	}

}