package lt.bumbis.rpsim;

import static org.junit.Assert.*;

import org.drools.io.ResourceFactory;
import org.junit.Before;
import org.junit.Test;

public class SimulationModelTest {
	
	private SimulationModel sim;
	private ProcessEngineImpl engine;
	
	@Before
	public void before() {
		sim = new SimulationModel(null, "Test Model", false, false);
		engine = new ProcessEngineImpl();
	}

	@Test
	public void testDescription() {
		assertNotNull("Description is null", sim.description());
	}
	
	@Test
	public void testInit() {
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet1.xml"));
		sim.setProcessEngine(engine);
		sim.init();
		assertNotNull("Process Engine is not started by Simulation Model", engine.getKnowledgeSession());
	}
	
	@Test
	public void testStartProcess() {
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet1.xml"));
		sim.setProcessEngine(engine);
		sim.init();
		assertNotNull("ProcessEntity is null", sim.startProcess());
	}
}
