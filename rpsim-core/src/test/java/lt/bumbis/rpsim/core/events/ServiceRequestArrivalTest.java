package lt.bumbis.rpsim.core.events;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimulationEngine;
import lt.bumbis.rpsim.core.entities.SvcReqExec;
import lt.bumbis.rpsim.core.entities.SvcReq;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.TimeInstant;

public class ServiceRequestArrivalTest {
	
	private SimulationEngine model;
	private Experiment exp;
	private SvcReq serviceRequest;
	private ServiceRequestArrival event;
	
	@Before
	public void runBeforeClass() {
		model = new SimulationEngine(null, "Test model", false, false);
		exp = new Experiment("Test Experiment", false);		
		model.connectToExperiment(exp);
		serviceRequest = new SvcReq(model, "Test SR", false);
		serviceRequest.setParameters(
				new Queue<SvcReq>(model, "Test wait queue", false, false),
				new Queue<SvcReqExec>(model, "Test idel queue", false, false),
				new TestDist(model, "Test distribution", false, false),
				TimeUnit.SECONDS,
				null);
		event = new ServiceRequestArrival(model, "Test event", false);
	}
	
	/**
	 * Test scenario when ServiceProcessor is unavailable
	 */
	@Test
	public void testEventRoutine1() {
		event.eventRoutine(serviceRequest);
		assertTrue(serviceRequest.getProcIdleQueue().isEmpty());
		assertEquals(serviceRequest.getWaitQueue().first(), serviceRequest);
		assertTrue(serviceRequest.getScheduledEvents().isEmpty());
	}
	
	/**
	 * Test scenario when ServiceProcessor is available
	 */
	@Test
	public void testEventRoutine2() {
		SvcReqExec serviceProcessor = new SvcReqExec(model, "Test Service Processor", false);
		serviceRequest.getProcIdleQueue().insert(serviceProcessor);
		event.eventRoutine(serviceRequest);
		assertTrue(serviceRequest.getProcIdleQueue().isEmpty());
		assertTrue(serviceRequest.getWaitQueue().isEmpty());
		assertEquals(serviceProcessor, serviceRequest.getServiceProcessor());
		assertEquals(ServiceRequestCompletion.class, serviceRequest.getScheduledEvents().get(0).getClass());
		assertEquals(new TimeInstant(33, TimeUnit.SECONDS), serviceRequest.scheduledNext());
	}

	private class TestDist extends ContDist {

		public TestDist(Model arg0, String arg1, boolean arg2, boolean arg3) {
			super(arg0, arg1, arg2, arg3);
		}

		@Override
		public Double sample() {
			return 33.0;
		}
		
	}

}
