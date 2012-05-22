package lt.bumbis.rpsim.droolsjbpm;

import org.drools.event.process.ProcessNodeTriggeredEvent;
import org.drools.event.process.ProcessStartedEvent;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkflowProcessInstance;
import org.jbpm.workflow.instance.node.RuleSetNodeInstance;

import lt.bumbis.rpsim.core.ISimEngine;

public class EventListenerRules extends EventListenerDefault {
	
	private StatefulKnowledgeSession ksession;

	public EventListenerRules(ISimEngine simEngine, StatefulKnowledgeSession ksession) {
		super(simEngine);
		this.ksession = ksession;
	}
	
	@Override
	public void beforeProcessStarted(ProcessStartedEvent event) {
		super.beforeProcessStarted(event);
		ksession.insert((WorkflowProcessInstance)event.getProcessInstance());
	}
	
	@Override
	public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
		super.afterNodeTriggered(event);
		if (event.getNodeInstance().getClass().equals(RuleSetNodeInstance.class)) {
			ksession.fireAllRules();
		}		
	}

}
