package lt.bumbis.rpsim.core.entities;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.TestDist;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;

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
		model.connectToExperiment(exp);
	}

	@Test
	public void testAll() {
		SvcProcessor svcProc = model.getSvcProcessor("SvcProc1");
		SvcReq svcPreq1 = new SvcReq(model, "Req1", false);
		SvcReq svcPreq2 = new SvcReq(model, "Req2", false);
		
		svcProc.add(svcPreq1);
		assertTrue(svcProc.isAvailable());
		svcProc.start(svcPreq1);
		assertTrue(!svcProc.isAvailable());
		assertTrue(!svcProc.haveRequest());

		svcProc.add(svcPreq2);
		assertTrue(svcProc.haveRequest());

		svcProc.complete(svcPreq1);
		assertTrue(svcProc.isAvailable());
		assertEquals(svcPreq2, svcProc.startNext());
		assertTrue(!svcProc.isAvailable());
		assertTrue(!svcProc.haveRequest());
		svcProc.complete(svcPreq2);
	}
}
