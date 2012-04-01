package lt.bumbis.rpsim.core;

import lt.bumbis.rpsim.core.ProcessEngineImpl;
import lt.bumbis.rpsim.core.SimulationModel;

import org.drools.io.ResourceFactory;
import org.jbpm.test.JbpmJUnitTestCase;
import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Model;


public class SimualtionModelTest extends JbpmJUnitTestCase {
	
	private SimulationModel model;
	private ProcessEngineImpl engine;
	
	@Before
	public void before() {
		model = new SimulationModel(null, "Test Model", true, false);
		engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		model.setProcessEngine(engine);
		model.setStartProcessName("changeSet2_process2");
	}

	@Test
	public void testInit() {
		model.init();
		assertNotNull("ProcessEngine not initialized",((ProcessEngineImpl)model.getProcessEngine()).getKnowledgeSession());
	}
	
//	@Test
//	public void testStartProcess() {
//		model.getProcessEngine().startEngine();
//		assertProcessInstanceActive(model.startProcess(), engine.getKnowledgeSession());
//	}
}
