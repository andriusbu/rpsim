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

public class ServiceRequestArrivalTest {
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;
	private SvcProcessor svcProc;
	private SvcReq svcPreq1;
	private SvcReq svcPreq2;
	private ServiceRequestArrival event;

	@Before
	public void setUp() throws Exception {
		conf = new SimConfig() {
			public void configure() {
				name("TestModel").showInReport(false).showInTrace(false);
				add(new ServiceProcessor("SvcProc1", 1, "Dist", TimeUnit.MINUTES, false, false));
				add(new Distribution("Dist", TestDist.class, new Object[] {}, false, false));
			}
		};
		conf.configure();
		model = new SimModel(conf);
		exp = new Experiment("TestExperiment",false);
		exp.setShowProgressBar(false);
		exp.setSilent(true);
		model.connectToExperiment(exp);
		svcProc = model.getSvcProcessor("SvcProc1");
		svcPreq1 = new SvcReq(new TestHandler(), model, "Req1", false);
		svcPreq2 = new SvcReq(new TestHandler(), model, "Req2", false);
		event = new ServiceRequestArrival(model, "Event", false);
	}

	@Test
	public void testEventRoutine_procAvailable() {

		event.eventRoutine(svcPreq1, svcProc);
		assertTrue(!svcProc.isAvailable());
		assertTrue(!svcProc.haveRequest());
		assertTrue(svcPreq1.isScheduled());
	}
	
	@Test
	public void testEventRoutine_procNotAvailable() {
		event.eventRoutine(svcPreq2, svcProc);
		event.eventRoutine(svcPreq1, svcProc);
		assertTrue(!svcProc.isAvailable());
		assertTrue(svcProc.haveRequest());
		assertTrue(!svcPreq1.isScheduled());
		assertTrue(svcPreq2.isScheduled());
	}
	
//	@Test
//	public void testEventRoutine_startTime() {
//		exp.stop(new TimeInstant(10, TimeUnit.MINUTES));
//		exp.start();
//		event.eventRoutine(svcPreq1, svcProc);
//		assertEquals(600.0, svcPreq1.getStartTime(), 0);
//		exp.finish();
//	}

}
