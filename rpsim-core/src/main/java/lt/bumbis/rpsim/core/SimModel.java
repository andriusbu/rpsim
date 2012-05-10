package lt.bumbis.rpsim.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.entities.ProcessEvent;
import lt.bumbis.rpsim.core.entities.SvcProcessor;
import lt.bumbis.rpsim.core.entities.SvcReq;
import lt.bumbis.rpsim.core.events.EventArrival;
import lt.bumbis.rpsim.core.events.NewProcessToken;
import lt.bumbis.rpsim.core.events.ServiceRequestArrival;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TimerEvent;
import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;
import desmoj.core.statistic.Count;
import desmoj.core.statistic.Tally;

public class SimModel extends Model implements ISimEngine {
	
	private SimConfig config;
	private Map<String, ContDist> dists = new HashMap<String, ContDist>();
	private Map<String, NewProcessToken> tokenGens = new HashMap<String, NewProcessToken>();
	private Map<String, Count> tokenGeneratorCounter = new HashMap<String, Count>();
	private Map<String, SvcProcessor> svcProcessors = new HashMap<String, SvcProcessor>();
	private Map<String, Tally> svcProcessorWaitTime = new HashMap<String, Tally>();
	
	private Map<String, SvcProcessor> activityMapping = new HashMap<String, SvcProcessor>();

	private IProcessEngine processEngine;
	private SimClockObserver clockObserver;

	public SimModel(SimConfig config) {
		super(null, config.getName(), config.isShowInReport(), config.isShowInTrace());
		this.config = config;
	}

	@Override
	public String description() {
		// TODO Add description
		return "Description...";
	}

	@Override
	public void doInitialSchedules() {
		if (processEngine != null) {
			processEngine.startEngine();
			Experiment exp = getExperiment();
			clockObserver = new SimClockObserver(exp.getReferenceUnit(), processEngine);
			exp.getSimClock().addObserver(clockObserver);
		}
		ModelBuilder.doInitialSchedules(this);
	}

	@Override
	public void init() {
		ModelBuilder.init(this);
	}
	
	public void addDist(String name, ContDist dist) {
		this.dists.put(name, dist);
	}
	
	public ContDist getDist(String name) {
		return dists.get(name);
	}
	
	public void addTokenGenerator(String name, NewProcessToken procToken) {
		tokenGens.put(name, procToken);
	}
	
	public NewProcessToken getTokenGenerator(String name) {
		return tokenGens.get(name);
	}
	
	public Map<String, NewProcessToken> getTokenGenerators() {
		return tokenGens;
	}

	public IProcessEngine getProcessEngine() {
		return processEngine;
	}

	public void setProcessEngine(IProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	public SimConfig getConfig() {
		return config;
	}

	public SvcProcessor getActivity(String name) {
		return activityMapping.get(name);
	}

	public void addActivity(String name, SvcProcessor processor) {
		this.activityMapping.put(name, processor);
	}

	public SvcProcessor getSvcProcessor(String name) {
		return svcProcessors.get(name);
	}

	public void addSvcProcessor(String name, SvcProcessor processor) {
		this.svcProcessors.put(name, processor);
	}

	public void newServiceRequest(String name, IServiceHandler handler) {
		//TODO analyze possibility to move to separate class
		SvcProcessor svcProc = activityMapping.get(name);
		String svcProcName = config.getActivity(name).getProcessor();
		boolean svcProcShowInTrace = config.getSvcProc(svcProcName).isShowInTrace();
		SvcReq svcReq = new SvcReq(handler, this, svcProcName+"SR", svcProcShowInTrace);
		ServiceRequestArrival event = new ServiceRequestArrival(this, svcProcName+"_SRA", svcProcShowInTrace);
		event.schedule(svcReq, svcProc, new TimeSpan(1, TimeUnit.MICROSECONDS));
	}

	public void newEvent(String eventName) {
		ProcessEvent procEvent = new ProcessEvent(this, "ProcessEvent", false);
		EventArrival event = new EventArrival(this, "Event", false);
		TimerEvent timerEvent = this.config.getTimerEvent(eventName);
		event.schedule(procEvent, new TimeSpan(timerEvent.getTime(), timerEvent.getTimeUnit()));
	}

	public void addCounterForTokenGenerator(String name, Count count) {
		tokenGeneratorCounter.put(name, count);
	}
	
	public Count getCounterForTokenGenerator(String name) {
		return tokenGeneratorCounter.get(name);
	}
	
	public void addServiceProcessorWaitTimeCollector(String name, Tally tally) {
		svcProcessorWaitTime.put(name, tally);
	}
	
	public Tally getServiceProcessWaitTimeCollector(String name) {
		return svcProcessorWaitTime.get(name);
	}
}
