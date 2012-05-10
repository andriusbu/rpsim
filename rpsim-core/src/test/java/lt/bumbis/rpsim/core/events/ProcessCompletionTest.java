package lt.bumbis.rpsim.core.events;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.entities.Process;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class ProcessCompletionTest {
	
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
	public void testEventRoutine() {
		Process process = new Process(model, "Proc1", false);
		ProcessArrival arrivalEvent = new ProcessArrival(model, "ProcessArrivalEvent", false);
		exp.stop(new TimeInstant(1, TimeUnit.MINUTES));
		exp.start();
		arrivalEvent.eventRoutine(model.getProcessContainer(), process);
		
		ProcessCompletion completionEvent = new ProcessCompletion(model, "ProcessArrivalEvent", false);
		exp.stop(new TimeInstant(11, TimeUnit.MINUTES));
		exp.start();
		completionEvent.eventRoutine(model.getProcessContainer(), process);
		
		
		assertEquals(60, process.getStartTime(), 0);
		assertEquals(660, process.getCompletionTime(), 0);
		exp.finish();
	}

}
