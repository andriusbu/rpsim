package lt.bumbis.rpsim;

import java.util.concurrent.TimeUnit;

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

import desmoj.core.simulator.Model;


public class ProcessEngineImpl implements ProcessEngine {
	
	private static String resAgentName = "ProcessEngineAgent";	
	private KnowledgeAgent kagent;
	private StatefulKnowledgeSession ksession;
	
	//Constructor
	public ProcessEngineImpl() {
		init();
	}
	
	//Implementation of interface methods
	public ProcessEngineImpl startEngine() {
		startEngine(false);
		return this;
	}
	
	public ProcessEntityImpl startProcess(Model owner, String name, boolean showInTrace) {
		ProcessEntityImpl process = new ProcessEntityImpl(owner, name, showInTrace);
		process.setProcessInstance(ksession.startProcess(((SimulationModel)owner).getStartProcess()));
		return process;
	}
	
	
	//Other pulibc methods
	public void addChangeSet(Resource res) {
		kagent.applyChangeSet(res);
	}
	
	//Protected and private methods
	protected StatefulKnowledgeSession startEngine(boolean realtime) {
		KnowledgeBase kbase = kagent.getKnowledgeBase();
		KnowledgeSessionConfiguration conf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
		if ( ! realtime ) conf.setOption( ClockTypeOption.get("pseudo"));
		ksession = kbase.newStatefulKnowledgeSession(conf, null);
		return ksession;
	}
	
//	public long step(long amount, TimeUnit unit) {
//		PseudoClockScheduler clock = ksession.getSessionClock();
//		return clock.advanceTime(amount, unit);
//	}

	public KnowledgeBase getKnowledgeBase() {
		return kagent.getKnowledgeBase();
	}
	
	public StatefulKnowledgeSession getKnowledgeSession() {
		return ksession;
	}
	
	private void init() {
		kagent = KnowledgeAgentFactory.newKnowledgeAgent( resAgentName );
	}
	

	
}
