package lt.bumbis.rpsim.droolsjbpm;

import lt.bumbis.rpsim.core.ISimEngine;

import org.drools.event.process.ProcessCompletedEvent;
import org.drools.event.process.ProcessEventListener;
import org.drools.event.process.ProcessNodeLeftEvent;
import org.drools.event.process.ProcessNodeTriggeredEvent;
import org.drools.event.process.ProcessStartedEvent;
import org.drools.event.process.ProcessVariableChangedEvent;
import org.jbpm.workflow.instance.node.TimerNodeInstance;

public class EventListener implements ProcessEventListener {
	
	private ISimEngine simEngine;
	
	public EventListener(ISimEngine simEngine) {
		this.simEngine = simEngine;
	}

	public void afterNodeLeft(ProcessNodeLeftEvent arg0) {
			
	}

	public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
		if (event.getNodeInstance().getClass().equals(TimerNodeInstance.class)) {
//			ActionNodeInstance node = (ActionNodeInstance) event.getNodeInstance();
			simEngine.newEvent(event.getNodeInstance().getNodeName());
		}
	}

	public void afterProcessCompleted(ProcessCompletedEvent event) {
//		System.out.println(event.getKnowledgeRuntime().getSessionClock().getCurrentTime() + ": Process completed " + event.getProcessInstance().getId());
		simEngine.newProcessCompletion(event.getProcessInstance().getProcessId(), event.getProcessInstance().getId());
	}

	public void afterProcessStarted(ProcessStartedEvent arg0) {
			
	}

	public void afterVariableChanged(ProcessVariableChangedEvent arg0) {
		
	}

	public void beforeNodeLeft(ProcessNodeLeftEvent arg0) {
		
	}

	public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
//		System.out.println(event.getKnowledgeRuntime().getSessionClock().getCurrentTime() + " : " + event.getProcessInstance().getId() +" : "+ event.getNodeInstance().getClass() + " : " + event.getNodeInstance().getNodeName());
		
		CustomWorkItemManager workItemManager = (CustomWorkItemManager)event.getKnowledgeRuntime().getWorkItemManager();
		workItemManager.setLastNode(event.getNodeInstance().getId());
		
		
	}

	public void beforeProcessCompleted(ProcessCompletedEvent arg0) {
		
	}

	public void beforeProcessStarted(ProcessStartedEvent event) {
		simEngine.newProcessArrival(event.getProcessInstance().getProcessId(), event.getProcessInstance().getId());		
	}

	public void beforeVariableChanged(ProcessVariableChangedEvent event) {
	}

}

