package lt.bumbis.rpsim.core.entities;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.TestDist;
import lt.bumbis.rpsim.core.TestHandler;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;
import desmoj.core.simulator.TimeSpan;

public class SvcProcessorTest {
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;

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
	}

	@Test
	public void testAll() {
		SvcProcessor svcProc = model.getSvcProcessor("SvcProc1");
		SvcReq svcPreq1 = new SvcReq(new TestHandler(), model, "Req1", false);
		SvcReq svcPreq2 = new SvcReq(new TestHandler(), model, "Req2", false);
		
		svcProc.add(svcPreq1);
		assertTrue(svcProc.isAvailable());
		svcProc.start(svcPreq1);
		assertTrue(!svcProc.isAvailable());
		assertTrue(!svcProc.haveRequest());

		svcProc.add(svcPreq2);
		assertTrue(svcProc.haveRequest());

		svcProc.complete(svcPreq1);
		assertTrue(svcProc.isAvailable());
		assertEquals(svcPreq2, svcProc.getNextRequest());
		svcProc.start(svcProc.getNextRequest());
		assertTrue(!svcProc.isAvailable());
		assertTrue(!svcProc.haveRequest());
		svcProc.complete(svcPreq2);
	}
	
	@Test
	public void testTiming() {
		SvcProcessor svcProc = model.getSvcProcessor("SvcProc1");
		SvcReq svcPreq1 = new SvcReq(new TestHandler(), model, "Req1", false);
		
		exp.stop(new TimeInstant(1, TimeUnit.MINUTES));
		exp.start();
		svcProc.add(svcPreq1);
		assertEquals(60, svcPreq1.getArrivalTime(), 0);
		assertEquals(0, svcPreq1.getStartTime(), 0);
		assertEquals(0, svcPreq1.getCompleteTime(), 0);
		
		exp.stop(new TimeInstant(2, TimeUnit.MINUTES));
		exp.start();
		svcProc.start(svcPreq1);
		assertEquals(60, svcPreq1.getArrivalTime(), 0);
		assertEquals(120, svcPreq1.getStartTime(), 0);
		assertEquals(0, svcPreq1.getCompleteTime(), 0);
		
		exp.stop(new TimeInstant(3, TimeUnit.MINUTES));
		exp.start();
		svcProc.complete(svcPreq1);
		assertEquals(60, svcPreq1.getArrivalTime(), 0);
		assertEquals(120, svcPreq1.getStartTime(), 0);	
		assertEquals(180, svcPreq1.getCompleteTime(), 0);
	}
}

