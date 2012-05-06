package lt.bumbis.rpsim.droolsjbpm;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.IProcessEngine;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.Resource;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.process.ProcessInstance;
import org.drools.time.impl.PseudoClockScheduler;

public class ProcessEngineImpl implements IProcessEngine, Observer {
    private static String resAgentName = "ProcessEngineAgent";

    private KnowledgeAgent kagent;
    private StatefulKnowledgeSession ksession;
    private PseudoClockScheduler clock;

    public ProcessEngineImpl() {
        init();
    }

    public ProcessEngineImpl startEngine() {
        KnowledgeBase kbase = kagent.getKnowledgeBase();
        KnowledgeSessionConfiguration conf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        conf.setOption( ClockTypeOption.get("pseudo"));
        ksession = kbase.newStatefulKnowledgeSession(conf, null);
        clock = ksession.getSessionClock();
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

    @Deprecated
    public void setAdvanceTime(final long amount, final TimeUnit unit) {
        clock.advanceTime(amount, unit);
    }

    @Deprecated
    public void setTime(final long time) {
        long currentTime = clock.getCurrentTime();
        long advanceTime = time - currentTime;
        if (advanceTime > 0) {
        	clock.advanceTime(advanceTime, TimeUnit.MILLISECONDS);
        }
    }

	public void update(Observable o, Object arg) {
		// TODO Implement time sync between SimulationEngine and ProcessEngine
		
	}

}
