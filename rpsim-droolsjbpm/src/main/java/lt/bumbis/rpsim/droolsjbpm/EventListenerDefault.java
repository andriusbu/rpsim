package lt.bumbis.rpsim.droolsjbpm;

import lt.bumbis.rpsim.core.ISimEngine;

import org.drools.event.process.ProcessCompletedEvent;
import org.drools.event.process.ProcessNodeTriggeredEvent;
import org.drools.event.process.ProcessStartedEvent;
import org.jbpm.workflow.instance.node.TimerNodeInstance;

public class EventListenerDefault extends EventListener {
	
	private ISimEngine simEngine;
	
	public EventListenerDefault(ISimEngine simEngine) {
		this.simEngine = simEngine;
	}
	
    @Override
	public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
        if (event.getNodeInstance().getClass().equals(TimerNodeInstance.class)) {
                simEngine.newEvent(event.getNodeInstance().getNodeName());
        }
    }

	@Override
	public void afterProcessCompleted(ProcessCompletedEvent event) {
		simEngine.newProcessCompletion(event.getProcessInstance().getProcessId(), event.getProcessInstance().getId());
	}

	@Override
	public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
		CustomWorkItemManager workItemManager = (CustomWorkItemManager)event.getKnowledgeRuntime().getWorkItemManager();
		workItemManager.setLastNode(event.getNodeInstance().getId());
	}

	public void beforeProcessStarted(ProcessStartedEvent event) {
		simEngine.newProcessArrival(event.getProcessInstance().getProcessId(), event.getProcessInstance().getId());		
	}
}

