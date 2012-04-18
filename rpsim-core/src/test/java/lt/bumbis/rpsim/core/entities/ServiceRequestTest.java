package lt.bumbis.rpsim.core.entities;

import static org.junit.Assert.*;
import lt.bumbis.rpsim.core.SimulationEngine;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Queue;

public class ServiceRequestTest {
	
	private class TestClass {
	}
	
	private SvcReq serviceRequest;
	private SimulationEngine model;
	private Experiment exp;
	
	@Before
	public void runBeforeClass() {
		model = new SimulationEngine(null, "Test model", false, false);
		exp = new Experiment("Test Experiment", false);		
		model.connectToExperiment(exp);
		serviceRequest = new SvcReq(model, "Test SR", false);
	}

	@Test
	public void testSettings() {		
		Queue<SvcReq> queue1 = new Queue<SvcReq>(model, "test", false, false);
		Queue<SvcReqExec> queue2 = new Queue<SvcReqExec>(model, "Test", false, false);
		TestClass testClass = new TestClass();
		serviceRequest.setParameters(queue1, queue2, null, null, testClass);
		assertEquals(serviceRequest.getWaitQueue(), queue1);
		assertEquals(serviceRequest.getProcIdleQueue(), queue2);
		assertEquals(serviceRequest.getExternalRef(), testClass);
	}

}
