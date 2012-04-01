package lt.bumbis.rpsim.core;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.ProcessEngineImpl;
import lt.bumbis.rpsim.core.SimulationModel;

import org.drools.io.ResourceFactory;
import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class TokenGeneratorTest {

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
	public void testEventRoutine() {
		Experiment exp = new Experiment("Test Experiment", false);
		exp.setShowProgressBar(false);
		model.connectToExperiment(exp);
		exp.stop(new TimeInstant(1, TimeUnit.MINUTES));
		exp.start();
		assertEquals(1, ((ProcessEngineImpl)model.getProcessEngine()).getKnowledgeSession().getProcessInstances().size());
		exp.stop(new TimeInstant(11, TimeUnit.MINUTES));
		exp.start();
		assertEquals(1, ((ProcessEngineImpl)model.getProcessEngine()).getKnowledgeSession().getProcessInstances().size());
		exp.finish();
	}

}
