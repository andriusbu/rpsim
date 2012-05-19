package lt.bumbis.rpsim.tests;

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
import org.junit.Before;
import org.junit.Test;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class ProcessSimulationTest1 {
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		conf = new SimConfig() {
			public void configure() {
				name("TestModel").showInReport(false).showInTrace(false)
				.add(new TokenGenerator("TG1", "changeSet3_process1", "DistArrival", TimeUnit.MINUTES, false, false))
				.add(new ServiceProcessor("SvcProc1", 5, "DistService", TimeUnit.MINUTES, false, false))
				.add(new Distribution("DistArrival", ContDistExponential.class, new Object[] {3.0}, false, false))
				.add(new Distribution("DistService", ContDistUniform.class, new Object[] {3.0, 7.0}, false, false))
				.add(new Activity("1", "SvcProc1"))
				.add(new Activity("3", "SvcProc1"))
				.add(new Activity("4", "SvcProc1"))
				.add(new TimerEvent("Catch", 6, TimeUnit.MINUTES, false, false));
			}
		};
		conf.configure();
		model = new SimModel(conf);
		
		ProcessEngineImpl procEngine = new ProcessEngineImpl(model);
		procEngine.addChangeSet(ResourceFactory.newClassPathResource("changeSet3.xml"));
		model.setProcessEngine(procEngine);
		
		exp = new Experiment("TestExperiment",false);
		model.connectToExperiment(exp);
		exp.setShowProgressBar(false);
		exp.setSilent(true);
		
		exp.stop(new TimeInstant(10, TimeUnit.MINUTES));
		exp.start();
		exp.report();
		exp.finish();
	}
}
