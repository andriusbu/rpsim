package lt.bumbis.rpsim.test;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.Activity;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TimerEvent;
import lt.bumbis.rpsim.core.simconfig.TokenGenerator;
import lt.bumbis.rpsim.droolsjbpm.ProcessEngineImpl;

import org.drools.io.ResourceFactory;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class Simulation1 {
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;
	
	public static void main(String[] args) {
		Simulation1 sim = new Simulation1();
		sim.test();
	}


	public void test() {
		conf = new SimConfig("Simulation1 Model", true, true);
		conf
			.add(new TokenGenerator("TG1", "changeSet3_process1", "DistArrival", TimeUnit.MINUTES, true, true))
			.add(new ServiceProcessor("SvcProc Test", 3, "DistService", TimeUnit.MINUTES, true, true))
			.add(new ServiceProcessor("SvcProc Human Task", 5, "DistService", TimeUnit.MINUTES, true, true))
			.add(new ServiceProcessor("SvcProc User Task", 10, "DistService", TimeUnit.MINUTES, true, true))
			.add(new Distribution("DistArrival", ContDistExponential.class, new Object[] {3.0}, true, true))
			.add(new Distribution("DistService", ContDistUniform.class, new Object[] {3.0, 7.0}, true, true))
			.add(new Activity("Test", "SvcProc Test"))
			.add(new Activity("Human Task", "SvcProc Human Task"))
			.add(new Activity("Manual Task", "SvcProc User Task"))
			.add(new TimerEvent("Catch", 5, TimeUnit.MINUTES, true, true));
		
		model = new SimModel(conf);
		
		ProcessEngineImpl procEngine = new ProcessEngineImpl(model);
		procEngine.addChangeSet(ResourceFactory.newClassPathResource("changeSet3.xml"));
		model.setProcessEngine(procEngine);
		
		exp = new Experiment("Simulatio1 Experiment",true);
		model.connectToExperiment(exp);
		exp.setShowProgressBar(true);
		
		exp.stop(new TimeInstant(1, TimeUnit.DAYS));
		exp.start();
		exp.report();
		exp.finish();
		
	}
}
