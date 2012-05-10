package lt.bumbis.rpsim.droolsjbpm;

import java.util.concurrent.TimeUnit;

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

	public void afterProcessCompleted(ProcessCompletedEvent arg0) {

	}

	public void afterProcessStarted(ProcessStartedEvent arg0) {
			
	}

	public void afterVariableChanged(ProcessVariableChangedEvent arg0) {
		
	}

	public void beforeNodeLeft(ProcessNodeLeftEvent arg0) {
		
	}

	public void beforeNodeTriggered(ProcessNodeTriggeredEvent arg0) {
		
	}

	public void beforeProcessCompleted(ProcessCompletedEvent arg0) {
		
	}

	public void beforeProcessStarted(ProcessStartedEvent arg0) {
		
	}

	public void beforeVariableChanged(ProcessVariableChangedEvent arg0) {
		
	}

}
