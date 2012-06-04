package lt.bumbis.rpsim.droolsjbpm;

import lt.bumbis.rpsim.core.ISimEngine;

import org.drools.event.process.ProcessCompletedEvent;
import org.drools.event.process.ProcessNodeLeftEvent;
import org.drools.event.process.ProcessNodeTriggeredEvent;
import org.drools.event.process.ProcessStartedEvent;
import org.jbpm.workflow.instance.node.TimerNodeInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListenerDefault extends EventListener {

	final static Logger logger = LoggerFactory
			.getLogger(EventListenerDefault.class);

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
		simEngine.newProcessCompletion(event.getProcessInstance()
				.getProcessId(), event.getProcessInstance().getId());
	}

	@Override
	public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
		logger.debug("BeforeNodeTriggeredEvent - Node class/name:"
				+ event.getNodeInstance().getClass() + "/"
				+ event.getNodeInstance().getNodeName() + "/"
				+ event.getNodeInstance().getId());
		CustomWorkItemManager workItemManager = (CustomWorkItemManager) event
				.getKnowledgeRuntime().getWorkItemManager();
		workItemManager.setLastNode(event.getNodeInstance().getId());
	}

	public void beforeProcessStarted(ProcessStartedEvent event) {
		logger.debug("BeforeProcessStartedEvent - ProcessId:"
				+ event.getProcessInstance().getProcessId());
		simEngine.newProcessArrival(event.getProcessInstance().getProcessId(),
				event.getProcessInstance().getId());
	}
	
	@Override
	public void beforeNodeLeft(ProcessNodeLeftEvent event) {
		logger.debug("BeforeNodeLeftEvent" + event.getNodeInstance().getClass() + "/"
				+ event.getNodeInstance().getNodeName()+ "/"
				+ event.getNodeInstance().getId());
	}
}
