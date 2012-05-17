package lt.bumbis.rpsim.droolsjbpm;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.drools.KnowledgeBase;
import org.drools.SessionConfiguration;
import org.drools.event.process.ProcessEventListener;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.process.ProcessInstance;
import org.drools.time.impl.PseudoClockScheduler;

import lt.bumbis.rpsim.core.IProcessEngine;
import lt.bumbis.rpsim.core.ISimEngine;

public abstract class ProcessEngine implements IProcessEngine {
	
	private ISimEngine simEngine;
    private StatefulKnowledgeSession ksession;
    private PseudoClockScheduler clock;
    private KnowledgeBase kbase;


    public ProcessEngine() {
    	init();
    }
    
    public ProcessEngine(ISimEngine simEngine) {
    	this.simEngine = simEngine;
    	init();
    }
    
    protected abstract void init();
    
    protected abstract KnowledgeBase newKnowledgeBase();
    
    public ProcessEngine startEngine() {
    	
    	this.kbase = newKnowledgeBase();
    	
    	Properties properties = new Properties();
        properties.put("drools.workItemManagerFactory", "lt.bumbis.rpsim.droolsjbpm.CustomWorkItemManagerFactory");
        SessionConfiguration conf = new SessionConfiguration(properties);
        conf.setOption( ClockTypeOption.get("pseudo"));
                
        ksession = kbase.newStatefulKnowledgeSession(conf, null);
        clock = ksession.getSessionClock();
        if (getSimEngine() != null) {
        	ProcessEventListener eventListener = new EventListener(getSimEngine());
        	ksession.addEventListener(eventListener);
        }
        CustomWorkItemHandler handler =  new CustomWorkItemHandler(getSimEngine());
        ksession.getWorkItemManager().registerWorkItemHandler("", handler);
        return this;
    }
    
    public long startProcess(final String processName) {
        ProcessInstance process = ksession.startProcess(processName);
        if (process == null) {
            return 0;
        } else {
        	return process.getId();
    	}
    }
    
    public long startProcess(String processName, Map<String, Object> data) {
    	ProcessInstance process = ksession.startProcess(processName, data);
    	if ( process == null ) {
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
    
	//Getter & Setters
    public KnowledgeBase getKnowledgeBase() {
        return this.kbase;
    }

    public StatefulKnowledgeSession getKnowledgeSession() {
        return this.ksession;
    }

	public ISimEngine getSimEngine() {
		return simEngine;
	}

	public void setSimEngine(ISimEngine simEngine) {
		this.simEngine = simEngine;
	}
}
