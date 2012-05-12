package lt.bumbis.rpsim.core;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.entities.ProcessEvent;
import lt.bumbis.rpsim.core.events.EventArrival;
import lt.bumbis.rpsim.core.simconfig.Activity;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TimerEvent;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class SimModelTest {

	private SimConfig conf;
	private SimModel model;
	private Experiment exp;
	private TestHandler handler;
	private TestProcessEngine procEngine;
	
	@Before
	public void setUp() throws Exception {
		conf = new SimConfig("TestModel", false, false);
		conf
			.add(new ServiceProcessor("SvcProc1", 1, "Dist", TimeUnit.MINUTES, false, false))
			.add(new Distribution("Dist", TestDist.class, new Object[] {}, false, false))
			.add(new Activity("Activity1", "SvcProc1"))
			.add(new TimerEvent("Event1", 10, TimeUnit.MINUTES, false, false));
		model = new SimModel(conf);
		procEngine = new TestProcessEngine(model, "Activity1");
		model.setProcessEngine(procEngine);
		exp = new Experiment("TestExperiment",false);
		model.connectToExperiment(exp);
		handler = new TestHandler();
	}

	@Test
	public void testNewServiceRequest() {
		model.newServiceRequest("Activity1", handler);
		assertEquals(4, model.getEntities(false).size());
		assertTrue(model.getEntities(false).get(1).isScheduled());
		assertTrue(model.getEntities(false).get(2).isScheduled());
		exp.stop(new TimeInstant(34, TimeUnit.MINUTES));
		exp.start();
		assertTrue(!model.getEntities(false).get(1).isScheduled());
		assertEquals(1, handler.getUpdateCalled());
		exp.finish();
	}
	
	@Test
	public void testDoInitialSchedules_procEnginStart() {
		model.doInitialSchedules();
		assertTrue("Process engine not started", procEngine.isEngineStarted());
	}
	
	@Test
	public void testNewEvent() {
		model.newEvent("Event1");
		assertEquals(ProcessEvent.class, model.getEntities(false).get(1).getClass());
		assertTrue("Event is not scheduled", model.getEntities(false).get(1).isScheduled());
		assertEquals(EventArrival.class, model.getEntities(false).get(1).getScheduledEvents().get(0).getClass());
		assertEquals(600, model.getEntities(false).get(1).getScheduledEvents().get(0).scheduledNext().getTimeRounded(TimeUnit.SECONDS));
	}
	
	@Test
	public void testProcessArrivalCompleteion() {
		model.newProcessArrival("Proc1", 1 );
		assertTrue("Prcess not saved as active", model.getActiveProcesses().containsKey((long)1));
		exp.stop(new TimeInstant(0));
		exp.start();
		assertEquals(1, model.getProcessContainer().getActiveProcessQueue().length());
		
		model.newProcessCompletion("Proc1", 1);
		assertTrue(!model.getActiveProcesses().containsKey((long)1));
		exp.stop(new TimeInstant(0));
		exp.start();
		assertEquals(0, model.getProcessContainer().getActiveProcessQueue().length());
		exp.finish();
	}
}
