package lt.bumbis.rpsim.core.events;

import static org.junit.Assert.*;
import lt.bumbis.rpsim.core.SimulationEngine;
import lt.bumbis.rpsim.core.entities.ServiceProcessor;
import lt.bumbis.rpsim.core.entities.ServiceRequest;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Queue;

public class ServiceRequestCompletionTest {
	
	private SimulationEngine model;
	private Experiment exp;
	private ServiceRequest serviceRequest;
	private ServiceProcessor serviceProcessor;
	private ServiceRequestCompletion event;
	
	
	@Before
	public void runBeforeClass() {
		model = new SimulationEngine(null, "Test model", false, false);
		exp = new Experiment("Test Experiment", false);		
		model.connectToExperiment(exp);
		serviceRequest = new ServiceRequest(model, "Test SR", false);
		serviceRequest.setParameters(
				new Queue<ServiceRequest>(model, "Test wait queue", false, false),
				new Queue<ServiceProcessor>(model, "Test idel queue", false, false),
				null,
				null,
				null);
		serviceProcessor = new ServiceProcessor(model, "Test Service Processor", false);
		serviceRequest.setServiceProcessor(serviceProcessor);
		event = new ServiceRequestCompletion(model, "Test event", false);
	}
	
	/**
	 * Test scenario when ServiceRequest wait queue is empty
	 */
	@Test
	public void testEventRoutine1() {
		event.eventRoutine(serviceRequest);
		assertTrue("Wait queue is not empty", serviceRequest.getWaitQueue().isEmpty());
		assertEquals(serviceProcessor, serviceRequest.getProcIdleQueue().get(0));
		assertTrue("Events was scheduled but hadn't", serviceRequest.getScheduledEvents().isEmpty());
		assertNull(serviceRequest.getServiceProcessor());
		//TODO check process engine notification
		
	}

}
