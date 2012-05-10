package lt.bumbis.rpsim.tests;

import static org.junit.Assert.*;

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

public class ProcessSimulationTest {
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		conf = new SimConfig("TestModel", false, false);
		conf
			.add(new TokenGenerator("TG1", "changeSet2_process1", "DistArrival", TimeUnit.MINUTES, false, false))
			.add(new ServiceProcessor("SvcProc1", 5, "DistService", TimeUnit.MINUTES, false, false))
			.add(new Distribution("DistArrival", ContDistExponential.class, new Object[] {3.0}, false, false))
			.add(new Distribution("DistService", ContDistUniform.class, new Object[] {3.0, 7.0}, false, false))
			.add(new Activity("ScriptTask_1", "SvcProc1"))
			.add(new TimerEvent("Catch", 6, TimeUnit.MINUTES, false, false));
		
		model = new SimModel(conf);
		
		ProcessEngineImpl procEngine = new ProcessEngineImpl(model);
		procEngine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		model.setProcessEngine(procEngine);
		
		exp = new Experiment("TestExperiment",false);
		model.connectToExperiment(exp);
		exp.setShowProgressBar(false);
		
		exp.stop(new TimeInstant(30, TimeUnit.MINUTES));
		exp.start();
		exp.finish();
		
	}
}
