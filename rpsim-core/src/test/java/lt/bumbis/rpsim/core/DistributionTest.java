package lt.bumbis.rpsim.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.Experiment;

public class DistributionTest {
	
	private SimModel model;
	private Experiment exp;

	@Before
	public void setUp() throws Exception {
		model = new SimModel(null, "Test Model", true, false);
		exp = new Experiment("Test Experiment", false);
		model.connectToExperiment(exp);		
	}

	@Test
	public void testInit_testDist() {
		Distribution dist = new Distribution(
				"TestDist",
				TestDist.class,
				new Object[] {},
				true,
				false);
		dist.init(model);
		assertEquals(33.0, dist.getDist().sample(), 0);
	}
	
	@Test
	public void testInit_ContDistUniform() {
		Distribution dist = new Distribution(
				"TestDist",
				ContDistUniform.class,
				new Object[] {3.0, 7.0},
				true,
				false);
		dist.init(model);
		assertEquals(3.0, dist.getDist().sample(), 1.0);
	}
	
	@Test
	public void testInit_ContDistExponential() {
		Distribution dist = new Distribution(
				"TestDist",
				ContDistExponential.class,
				new Object[] {3.0},
				true,
				false);
		dist.init(model);
		assertEquals(0.0, dist.getDist().sample(), 1.0);
	}


//	@Test
	public void testGetDist() {
		fail("Not yet implemented");
	}

}
