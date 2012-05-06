package lt.bumbis.rpsim.core.events;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.TestDist;
import lt.bumbis.rpsim.core.TestProcessEngine;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TokenGenerator;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class NewProcessEngineTest {
	
	private SimConfig conf;
	private Experiment exp;
	private SimModel model;
	private TestProcessEngine procEngine;
	private NewProcessToken tokenGen;

	@Before
	public void setUp() throws Exception {
		conf = new SimConfig("TestModel", false, false);
		conf
			.add(new TokenGenerator("TG1", "Process", "Dist1", TimeUnit.MINUTES, false, false))
			.add(new Distribution("Dist1", TestDist.class, new Object[] {}, false, false));
		model = new SimModel(conf);
		procEngine = new TestProcessEngine();
		model.setProcessEngine(procEngine);
		exp = new Experiment("TestExperiment",false);
		model.connectToExperiment(exp);
		tokenGen = model.getTokenGenerator("TG1");
	}

	@Test
	public void testEventRoutine1() {
		tokenGen.eventRoutine();
		assertEquals(1, procEngine.getProcStartCount());
		assertEquals("Process", procEngine.getLastProcessName());
	}
	
	@Test
	public void testEventRoutine2() {
		
		exp.stop(new TimeInstant(70, TimeUnit.MINUTES));
		exp.start();
		assertEquals(2, procEngine.getProcStartCount());
		assertEquals("Process", procEngine.getLastProcessName());
		exp.finish();
	}

}
