package lt.bumbis.rpsim.core.entities;

import static org.junit.Assert.*;
import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.SimConfig;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;

public class ResPoolTest {
	
	private SimConfig config;
	private SimModel model;
	private Experiment exp;

	@Before
	public void setUp() throws Exception {
		config = new SimConfig() {
			public void configure() {
				name("TestModel").showInReport(false).showInTrace(false);
			}
		};
		config.configure();
		model = new SimModel(config);
		exp = new Experiment("TestExperiment",false);
		exp.setShowProgressBar(false);
		model.connectToExperiment(exp);
	}

	@Test
	public void test() {
		ResPool resPool = new ResPool(model, "ResPool1", 3, false, false);
		assertEquals(3, resPool.getResQueue().length());
		assertTrue(resPool.getSvcRequestWaitQueue().isEmpty());
	}

}
