package lt.bumbis.rpsim.core.events;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.TestDist;
import lt.bumbis.rpsim.core.TestHandler;
import lt.bumbis.rpsim.core.entities.SvcProcessor;
import lt.bumbis.rpsim.core.entities.SvcReq;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;

public class ServiceRequestCompletionTest {
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;
	private SvcProcessor svcProc;
	private SvcReq svcPreq1;
	private SvcReq svcPreq2;
	private ServiceRequestCompletion event;
	private TestHandler handler = new TestHandler();

	@Before
	public void setUp() throws Exception {
		conf = new SimConfig("TestModel", false, false);
		conf
			.add(new ServiceProcessor("SvcProc1", 1, "Dist", TimeUnit.MINUTES, false, false))
			.add(new Distribution("Dist", TestDist.class, new Object[] {}, false, false));
		model = new SimModel(conf);
		exp = new Experiment("TestExperiment",false);
		exp.setShowProgressBar(false);
		model.connectToExperiment(exp);
		svcProc = model.getSvcProcessor("SvcProc1");
		svcPreq1 = new SvcReq(handler, model, "Req1", false);
		svcPreq2 = new SvcReq(handler, model, "Req2", false);
		event = new ServiceRequestCompletion(model, "Event", false);
	}

	@Test
	public void testEventRoutine_noWatingReq() {
		svcProc.add(svcPreq1);
		svcProc.start(svcPreq1);
		event.eventRoutine(svcPreq1, svcProc);
		assertTrue(svcProc.isAvailable());
		assertTrue(!svcProc.haveRequest());
		assertEquals(1, handler.getUpdateCalled());
	}
	
	@Test
	public void testEventRoutine_isWwaitingReq() {
		svcProc.add(svcPreq1);
		svcProc.start(svcPreq1);
		svcProc.add(svcPreq2);
		event.eventRoutine(svcPreq1, svcProc);
		assertTrue("No servivce processoes should be available", !svcProc.isAvailable());
		assertTrue("No service requests shall be in waitQueue", !svcProc.haveRequest());
		assertTrue("Second service request must be scheduled", svcPreq2.isScheduled());
		assertEquals(1, handler.getUpdateCalled());
	}

}
