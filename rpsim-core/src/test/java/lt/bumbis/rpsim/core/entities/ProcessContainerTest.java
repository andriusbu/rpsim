package lt.bumbis.rpsim.core.entities;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class ProcessContainerTest {
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;
	
	@Before
	public void setUp() throws Exception {
		conf = new SimConfig("TestModel", false, false);
		model = new SimModel(conf);
		exp = new Experiment("TestExperiment",false);
		exp.setShowProgressBar(false);
		model.connectToExperiment(exp);
	}
	
	@Test
	public void testProcessStartCompletionTime() {
		ProcessContainer procContainer = new ProcessContainer(model, "Container", false);
		Process proc1 = new Process(model, "Proc1", false);
		
		exp.stop(new TimeInstant(1, TimeUnit.MINUTES));
		exp.start();
		procContainer.startProcess(proc1);
		assertEquals(60, proc1.getStartTime(), 0);
		assertEquals(0, proc1.getCompletionTime(), 0);

		exp.stop(new TimeInstant(11, TimeUnit.MINUTES));
		exp.start();
		procContainer.completeProcess(proc1);
		assertEquals("Start time error", 60, proc1.getStartTime(), 0);
		assertEquals("Completion time error", 660, proc1.getCompletionTime(), 0);
		
		exp.finish();
	}
	
	@Test
	public void testTimeStats() {
		ProcessContainer procContainer = new ProcessContainer(model, "Container", false);
		Process proc1 = new Process(model, "Proc1", false);
		Process proc2 = new Process(model, "Proc2", false);
		
		exp.stop(new TimeInstant(1, TimeUnit.MINUTES));
		exp.start();
		procContainer.startProcess(proc1);
		
		exp.stop(new TimeInstant(11, TimeUnit.MINUTES));
		exp.start();
		procContainer.completeProcess(proc1);
		procContainer.startProcess(proc2);
		
		exp.stop(new TimeInstant(31, TimeUnit.MINUTES));
		exp.start();
		procContainer.completeProcess(proc2);
		
		assertEquals("Min exec time wrong", 600, procContainer.getExecTime().getMinimum(), 0);
		assertEquals("Max exec time wrong", 1200, procContainer.getExecTime().getMaximum(), 0);
		assertEquals("Mean exec time worng", 900, procContainer.getExecTime().getMean(), 0);
	}
	
	@Test
	public void testQueue() {
		ProcessContainer procContainer = new ProcessContainer(model, "Container", false);
		Process proc1 = new Process(model, "Proc1", false);
		Process proc2 = new Process(model, "Proc2", false);
		
		procContainer.startProcess(proc1);
		procContainer.startProcess(proc2);
		assertEquals(2, procContainer.getActiveProcessQueue().length());
		procContainer.completeProcess(proc2);
		assertEquals(1, procContainer.getActiveProcessQueue().length());
	}
}
