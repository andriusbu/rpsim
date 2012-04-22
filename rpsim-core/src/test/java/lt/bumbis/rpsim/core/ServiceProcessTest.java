package lt.bumbis.rpsim.core;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.Experiment;

public class ServiceProcessTest {
	
	private SimModel model;
	private Experiment exp;
	
	@Before
	public void doBefore() {
		model = new SimModel(null, "Test", false, false);
		exp = new Experiment("Test Experiment", false);
		model.connectToExperiment(exp);
	}

	@Test
	public void testInit_Queues() {
		ServiceProcessor svcProc = new ServiceProcessor(
				"TestSvcProc",
				5,
				TestDist.class,
				new Object[] {},
				TimeUnit.MINUTES,
				true,
				false
		);
		svcProc.init(model);
		assertEquals(true, svcProc.getWaitQueue().isEmpty());
		assertEquals(5, svcProc.getIdleQueue().length());
		assertEquals(33.0, svcProc.getServiceTimeDist().sample(), 0);
	}
	
	@Test
	public void testInit_TestDist() {
		ServiceProcessor svcProc = new ServiceProcessor(
				"TestSvcProc",
				5,
				TestDist.class,
				new Object[] {},
				TimeUnit.MINUTES,
				true,
				false
		);
		svcProc.init(model);
		assertEquals(33.0, svcProc.getServiceTimeDist().sample(), 0);
	}
	
	@Test
	public void testInit_ContDistUniform() {
		ServiceProcessor svcProc = new ServiceProcessor(
				"TestSvcProc",
				5,
				ContDistUniform.class,
				new Object[] {3.0, 7.0},
				TimeUnit.MINUTES,
				true,
				false
		);
		svcProc.init(model);
		assertEquals(3.0, svcProc.getServiceTimeDist().sample(), 1);
	}
	
	@Test
	public void testInit_ContDistExponential() {
		ServiceProcessor svcProc = new ServiceProcessor(
				"TestSvcProc",
				5,
				ContDistExponential.class,
				new Object[] {3.0},
				TimeUnit.MINUTES,
				true,
				false
		);
		svcProc.init(model);
		assertEquals(0, svcProc.getServiceTimeDist().sample(), 1);
	}
}
