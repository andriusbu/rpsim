package lt.bumbis.rpsim;

import static org.junit.Assert.*;

import org.drools.io.ResourceFactory;
import org.jbpm.test.JbpmJUnitTestCase;
import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class SimualtionModelTest extends JbpmJUnitTestCase {
	
	private SimulationModel model;
	private ProcessEngineImpl engine;
	
	@Before
	public void before() {
		model = new SimulationModel(null, "Test Model", true, false);
		engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		model.setProcessEngine(engine);
		model.setStartProcessName("changeSet2_process1");
	}

	@Test
	public void testDoInitialShedules() {
		Experiment exp = new Experiment("Test Experiment", false);
		exp.setShowProgressBar(false);
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
		assertNotNull("ProcessEngine not initialized",((ProcessEngineImpl)model.getProcessEngine()).getKnowledgeSession());
	}
	
	@Test
	public void testStartProcess() {
		model.getProcessEngine().startEngine();
		assertProcessInstanceActive(model.startProcess(), engine.getKnowledgeSession());
	}

}
