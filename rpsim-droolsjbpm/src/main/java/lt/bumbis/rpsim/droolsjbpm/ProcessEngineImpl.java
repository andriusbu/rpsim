package lt.bumbis.rpsim.droolsjbpm;

import lt.bumbis.rpsim.core.ISimEngine;

import org.drools.KnowledgeBase;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.Resource;

public class ProcessEngineImpl extends ProcessEngine {
    private static String resAgentName = "ProcessEngineAgent";
    private KnowledgeAgent kagent;
    
    public ProcessEngineImpl(ISimEngine simEngine) {
    	super(simEngine);
    }
    
    public ProcessEngineImpl() {
		super();
	}

	public void addChangeSet(final Resource res) {
        kagent.applyChangeSet(res);
    }

    @Override
    protected void init() {
        kagent = KnowledgeAgentFactory.newKnowledgeAgent( resAgentName );
    }

	@Override
	protected KnowledgeBase newKnowledgeBase() {
		return kagent.getKnowledgeBase();
	}
}
