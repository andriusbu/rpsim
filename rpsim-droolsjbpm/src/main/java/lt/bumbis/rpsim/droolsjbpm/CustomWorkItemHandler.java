package lt.bumbis.rpsim.droolsjbpm;

import lt.bumbis.rpsim.core.ISimEngine;

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomWorkItemHandler implements WorkItemHandler {

	final static Logger LOG = LoggerFactory
			.getLogger(CustomWorkItemHandler.class);

	private ISimEngine simEngine;

	public CustomWorkItemHandler(ISimEngine simEngine) {
		this.simEngine = simEngine;
	}

	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {

	}

	protected void executeWorkItem(WorkItem workItem, String nodeId,
			WorkItemManager workItemManager) {
		LOG.debug("Executing work item: " + workItem.getId() + "/"
				+ workItem.getName() + "/" + workItem.getParameter("TaskName") + "/" + nodeId);
		simEngine.newServiceRequest(nodeId, new ServiceRequestHandler(workItem,
				workItemManager));
	}

	public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
		LOG.debug(workItem.getClass().getName() + "/" + workItem.getName());
		if ( workItem.getName().equals("Human Task") ) {
			executeWorkItem(workItem, (String) workItem.getParameter("TaskName"), workItemManager);
		} else if (workItem.getName().equals("Email") ) {				
			executeWorkItem(workItem, (String) workItem.getName(), workItemManager);
		} else {
			executeWorkItem(workItem, ((CustomWorkItemManager)workItemManager).getLastNode().toString(), workItemManager  );
		}
	}

}