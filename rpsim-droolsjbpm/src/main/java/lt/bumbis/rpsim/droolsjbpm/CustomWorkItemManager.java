package lt.bumbis.rpsim.droolsjbpm;

import java.util.concurrent.atomic.AtomicLong;

import org.drools.WorkItemHandlerNotFoundException;
import org.drools.common.InternalKnowledgeRuntime;
import org.drools.process.instance.WorkItem;
import org.drools.process.instance.impl.DefaultWorkItemManager;
import org.drools.process.instance.impl.WorkItemImpl;
import org.drools.runtime.process.WorkItemHandler;

public class CustomWorkItemManager extends DefaultWorkItemManager {
	
	private CustomWorkItemHandler handler;
	private AtomicLong workItemCounter = new AtomicLong(0);
//	private StatefulKnowledgeSessionImpl ksession;
	private Long lastNode;
	
	public CustomWorkItemManager(InternalKnowledgeRuntime kruntime) {
		super(kruntime);
//		this.ksession = (StatefulKnowledgeSessionImpl) kruntime;
//		System.out.println(kruntime.getClass().getName());
	}
	
	@Override
    public void registerWorkItemHandler(String workItemName, WorkItemHandler handler) {
        this.handler = (CustomWorkItemHandler) handler;
    }
	
	@Override
	public void internalExecuteWorkItem(WorkItem workItem) {
		((WorkItemImpl) workItem).setId(workItemCounter.incrementAndGet());
	    internalAddWorkItem(workItem);
	    if (handler != null) {
//	            handler.executeWorkItem(workItem, this);
	    		handler.executeWorkItem(workItem, lastNode.toString(), this);
	        } else {
	        	throw new WorkItemHandlerNotFoundException( "Could not find work item handler for ", workItem.getName());
	        }
	    }
	
	   public void internalAbortWorkItem(long id) {
	        WorkItemImpl workItem = (WorkItemImpl) getWorkItem(new Long(id));
	        // work item may have been aborted
	        if (workItem != null) {
	            if (handler != null) {
	                handler.abortWorkItem(workItem, this);
	            } else {
	                removeWorkItem( workItem.getId() );
	                throw new WorkItemHandlerNotFoundException( "Could not find work item handler for " + workItem.getName(),
	                                                                 workItem.getName() );
	            }
	            removeWorkItem(workItem.getId());
	        }
	    }
	   
	   public void removeWorkItem(Long id) {
		   throw new WorkItemHandlerNotFoundException( "Not implemented", "!!!!!");
	   }

	   public void setLastNode(long id) {
		   this.lastNode = id;
	   }
	   
	   public Long getLastNode() {
		   return this.lastNode;
	   }
	
}
