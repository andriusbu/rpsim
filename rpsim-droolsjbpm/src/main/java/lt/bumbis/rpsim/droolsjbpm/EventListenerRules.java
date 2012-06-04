package lt.bumbis.rpsim.droolsjbpm;

import java.util.HashMap;
import java.util.Map;

import org.drools.event.process.ProcessCompletedEvent;
import org.drools.event.process.ProcessNodeLeftEvent;
import org.drools.event.process.ProcessNodeTriggeredEvent;
import org.drools.event.process.ProcessStartedEvent;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkflowProcessInstance;
import org.drools.runtime.rule.FactHandle;
import org.jbpm.workflow.instance.node.DynamicNodeInstance;
import org.jbpm.workflow.instance.node.RuleSetNodeInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lt.bumbis.rpsim.core.ISimEngine;

public class EventListenerRules extends EventListenerDefault {
	
	final static Logger logger = LoggerFactory
			.getLogger(EventListenerRules.class);

	private StatefulKnowledgeSession ksession;
	private Map<WorkflowProcessInstance, FactHandle> handles;

	public EventListenerRules(ISimEngine simEngine,
			StatefulKnowledgeSession ksession) {
		super(simEngine);
		this.ksession = ksession;
		handles = new HashMap<WorkflowProcessInstance, FactHandle>();
	}

	@Override
	public void beforeProcessStarted(ProcessStartedEvent event) {
		super.beforeProcessStarted(event);
		FactHandle handle = ksession.insert((WorkflowProcessInstance) event.getProcessInstance());
		handles.put((WorkflowProcessInstance) event.getProcessInstance(), handle);
	}
	
	@Override
	public void afterProcessCompleted(ProcessCompletedEvent event) {
		super.afterProcessCompleted(event);
		ksession.retract(handles.get((WorkflowProcessInstance) event.getProcessInstance()));
	}

	@Override
	public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
		super.afterNodeTriggered(event);
		if ( event.getNodeInstance().getClass().equals(RuleSetNodeInstance.class) ) {
			ksession.fireAllRules();
		} else
		if ( event.getNodeInstance().getClass().equals(DynamicNodeInstance.class) ) {
			ksession.insert((DynamicNodeInstance)event.getNodeInstance());
			ksession.fireAllRules();
//			ksession.signalEvent("Review", null, event.getProcessInstance().getId());
//			((DynamicNodeInstance)event.getNodeInstance()).getP
		}
	}

}
