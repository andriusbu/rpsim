package lt.bumbis.rpsim.core;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;

public class ServiceProcessTest {
	
	private SimModel model;
	private Experiment exp;
	private HashMap<String, Distribution> dists;
	private ServiceProcessor svcProc;
	
	@Before
	public void doBefore() {
		model = new SimModel(null, "Test", true, false);
		dists = new HashMap<String, Distribution>();
		dists.put("Test Dist",
				new Distribution(
						"Dist",
						TestDist.class,
						new Object[] {},
						true,
						false
				)
		);
		model.setDistributions(dists);
		exp = new Experiment("Test Experiment", false);
		model.connectToExperiment(exp);
		svcProc = new ServiceProcessor(
				"TestSvcProc",
				5,
				"Test Dist",
				TimeUnit.MINUTES,
				true,
				false
			);
	}

	@Test
	public void testInit_Queues() {
		svcProc.init(model);
		assertEquals(true, svcProc.getWaitQueue().isEmpty());
		assertEquals(5, svcProc.getIdleQueue().length());
	}
	
	@Test
	public void testInit_Dist() {
		svcProc.init(model);
		assertEquals(33.0, svcProc.getServiceTimeDist().sample(), 0);
	}
}
