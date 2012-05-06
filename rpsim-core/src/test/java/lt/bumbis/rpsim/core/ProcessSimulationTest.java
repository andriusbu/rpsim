package lt.bumbis.rpsim.core;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.simconfig.Activity;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TokenGenerator;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class ProcessSimulationTest {
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;
	private TestProcessEngine procEngine;

	@Before
	public void setUp() throws Exception {		
		conf = new SimConfig("TestModel", false, false);
		conf
			.add(new TokenGenerator("TG1", "Process", "Dist1", TimeUnit.MINUTES, false, false))
			.add(new ServiceProcessor("SvcProc1", 1, "Dist2", TimeUnit.MINUTES, false, false))
			.add(new Distribution("Dist1", TestDist.class, new Object[] {5.0}, false, false))
			.add(new Distribution("Dist2", TestDist.class, new Object[] {8.0}, false, false))
			.add(new Activity("Activity1", "SvcProc1"));
		model = new SimModel(conf);
		procEngine = new TestProcessEngine(model, "Activity1");
		model.setProcessEngine(procEngine);
		model.setTimeSyncHandler(procEngine);
		exp = new Experiment("TestExperiment",false);
		model.connectToExperiment(exp);
		exp.setShowProgressBar(false);
	}

	@Test
	public void test() {
		exp.stop(new TimeInstant(30, TimeUnit.MINUTES));
		exp.start();
		assertEquals(3, procEngine.getCompletedActivities());
		assertEquals(5, procEngine.getProcStartCount());
		assertEquals(14, procEngine.getTimeUpdateCount());
		exp.finish();
	}

}
