package lt.bumbis.rpsim.droolsjbpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.drools.runtime.rule.FactHandle;
import org.drools.KnowledgeBase;
import org.drools.SessionConfiguration;
import org.drools.event.process.ProcessEventListener;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.process.ProcessInstance;
import org.drools.time.impl.PseudoClockScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lt.bumbis.rpsim.core.IProcessEngine;
import lt.bumbis.rpsim.core.ISimEngine;

public abstract class ProcessEngine implements IProcessEngine {
	
	private final static Logger LOG = LoggerFactory.getLogger(ProcessEngine.class);
	private final static String DEFAUL_KLOG_PATH = "log/log.log";
	
	private ISimEngine simEngine;
    private StatefulKnowledgeSession ksession;
    private PseudoClockScheduler clock;
    private KnowledgeBase kbase;
    
    private boolean enableRules = false;
    private String logPath = DEFAUL_KLOG_PATH;
    private boolean enableLog = false;
    private KnowledgeRuntimeLogger klogger;
    
    private Map<ProcessInstance, List<FactHandle>> contextDataHandles = new HashMap<ProcessInstance, List<FactHandle>>();

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
        	ProcessEventListener eventListener;
        	if (isEnableRules() ) {
        		LOG.debug("Rules enabled > User EventListenerRules");
        		eventListener = new EventListenerRules(getSimEngine(), this, ksession);
        	} else {
        		LOG.debug("Rules disabled > User EventListenerDefault");
        		eventListener = new EventListenerDefault(getSimEngine());
        	}
        	ksession.addEventListener(eventListener);
        }
        
        if ( enableLog ) {
        	klogger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, this.logPath);
        }
        CustomWorkItemHandler handler =  new CustomWorkItemHandler(getSimEngine());
        ksession.getWorkItemManager().registerWorkItemHandler("", handler);
        return this;
    }
    
    public void stopEngine() {
    	klogger.close();
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
    
    public long startProcess(String processName, Map<String, Object> processData, Map<String, Object> contextData) {
    	ProcessInstance process = ksession.startProcess(processName, processData);
    	List<FactHandle> handleList = new ArrayList<FactHandle>();
    	for (Object obj: contextData.values()) {
			handleList.add(ksession.insert(obj));
		}
    	contextDataHandles.put(process, handleList);
    	if ( process == null ) {
    		return 0;
    	} else {
    		return process.getId();
    	}
    }
    
    public void stopProcess(ProcessInstance process) {
    	if ( contextDataHandles.containsKey(process) ) {
	    		List<FactHandle> handleList = contextDataHandles.get(process);
		    	for ( Iterator<FactHandle> i = handleList.iterator(); i.hasNext() ;) {
		    		ksession.retract(i.next());
		    	}
		    	contextDataHandles.remove(process);
    	}
    }
    
    public void addContextData(Object obj) {
    	ksession.insert(obj);
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

	public boolean isEnableRules() {
		return enableRules;
	}

	public void setEnableRules(boolean enableRules) {
		this.enableRules = enableRules;
	}
	
	public void enableLog() {
		this.enableLog = true;
	}
	
	public void enableLog(String path) {
		enableLog();
		this.logPath = path;
	}
}
