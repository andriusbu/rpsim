package lt.bumbis.rpsim;

import static org.junit.Assert.*;

import org.drools.io.ResourceFactory;
import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class SimualtionModelTest {
	
	private SimulationModel model;
	private ProcessEngineImpl engine;
	private Experiment exp;
	
	@Before
	public void before() {
		model = new SimulationModel(null, "Test Model", true, false);
		engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet1.xml"));
		model.setProcessEngine(engine);
		exp = new Experiment("Test Experiment", false);
		exp.setShowProgressBar(false);
	}

	@Test
	public void testDoInitialShedules() {
		model.connectToExperiment(exp);
		exp.stop(new TimeInstant(0));
		exp.start();
		exp.start();
		assertEquals(TokenGenerator.class ,model.current().getClass());
		exp.finish();
	}
	
	@Test
	public void testInit() {
		model.init();
		assertTrue("Process Engine not initialized", model.getProcessEngine().isRunning());
	}
	
	@Test
	public void testStartProcess() {
		fail("Not yet implemented");
	}

}
