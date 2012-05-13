package lt.bumbis.rpsim.droolsjbpm;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.IProcessEngine;
import lt.bumbis.rpsim.core.ISimEngine;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.event.process.ProcessEventListener;
import org.drools.io.Resource;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.process.ProcessInstance;
import org.drools.time.impl.PseudoClockScheduler;

public class ProcessEngineImpl implements IProcessEngine {
    private static String resAgentName = "ProcessEngineAgent";

    private KnowledgeAgent kagent;
    private StatefulKnowledgeSession ksession;
    private PseudoClockScheduler clock;
    private ISimEngine simEngine;

    public ProcessEngineImpl() {
        init();
    }
    
    public ProcessEngineImpl(ISimEngine simEngine) {
    	this.simEngine = simEngine;
    	init();
    }

    public ProcessEngineImpl startEngine() {
        KnowledgeBase kbase = kagent.getKnowledgeBase();
        KnowledgeSessionConfiguration conf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        conf.setOption( ClockTypeOption.get("pseudo"));
        ksession = kbase.newStatefulKnowledgeSession(conf, null);
        clock = ksession.getSessionClock();
        if (simEngine != null) {
        	ProcessEventListener eventListener = new EventListener(simEngine);
        	ksession.addEventListener(eventListener);
        }
        ActionHandler handler =  new ActionHandler(simEngine);
        ksession.getWorkItemManager().registerWorkItemHandler("Test", handler);
        ksession.getWorkItemManager().registerWorkItemHandler("Human Task", handler);
        ksession.getWorkItemManager().registerWorkItemHandler("Manual Task", handler);
        return this;
    }

    public void addChangeSet(final Resource res) {
        kagent.applyChangeSet(res);
    }

    private void init() {
        kagent = KnowledgeAgentFactory.newKnowledgeAgent( resAgentName );
    }

    public KnowledgeBase getKnowledgeBase() {
        return kagent.getKnowledgeBase();
    }

    public StatefulKnowledgeSession getKnowledgeSession() {
        return this.ksession;
    }

    public long startProcess(final String processName) {
        ProcessInstance process = ksession.startProcess(processName);
        if (process == null) {
            return 0;
        } else {
        	return process.getId();
    	}
    }

	public void syncTime(long time, TimeUnit timeUnit) {
		long currentTime = clock.getCurrentTime();
		long newTime = TimeUnit.MILLISECONDS.convert(time, timeUnit);
		long advanceTime = newTime - currentTime;
		if (advanceTime > 0) {
			clock.advanceTime(advanceTime, TimeUnit.MILLISECONDS);
		}
	}
}
