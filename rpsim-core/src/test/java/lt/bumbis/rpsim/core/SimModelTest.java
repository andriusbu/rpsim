package lt.bumbis.rpsim.core;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.simconfig.Activity;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class SimModelTest {

	private SimConfig conf;
	private SimModel model;
	private Experiment exp;
	private TestHandler handler;
	
	@Before
	public void setUp() throws Exception {
		conf = new SimConfig("TestModel", false, false);
		conf
			.add(new ServiceProcessor("SvcProc1", 1, "Dist", TimeUnit.MINUTES, false, false))
			.add(new Distribution("Dist", TestDist.class, new Object[] {}, false, false))
			.add(new Activity("Activity1", "SvcProc1"));
		model = new SimModel(conf);
		exp = new Experiment("TestExperiment",false);
		model.connectToExperiment(exp);
		handler = new TestHandler();
	}

	@Test
	public void testNewServiceRequest() {
		model.newServiceRequest("Activity1", handler);
		assertEquals(3, model.getEntities(false).size());
		assertTrue(model.getEntities(false).get(1).isScheduled());
		assertTrue(model.getEntities(false).get(2).isScheduled());
		exp.stop(new TimeInstant(34, TimeUnit.MINUTES));
		exp.start();
		assertTrue(!model.getEntities(false).get(1).isScheduled());
		assertEquals(1, handler.getUpdateCalled());
		exp.finish();
	}
	
	

}
