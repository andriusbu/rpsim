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

public class ProcessArrivalTest {
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;

	@Before
	public void setUp() throws Exception {
		conf = new SimConfig() {
			public void configure() {
				name("TestModel").showInReport(false).showInTrace(false);
			}
		};
		conf.configure();
		model = new SimModel(conf);
		exp = new Experiment("TestExperiment",false);
		exp.setShowProgressBar(false);
		exp.setSilent(true);
		model.connectToExperiment(exp);
	}

	@Test
	public void testEventRoutine() {
		Process process = new Process(model, "Proc1", false);
		ProcessArrival event = new ProcessArrival(model, "ProcessArrivalEvent", false);
		exp.stop(new TimeInstant(1, TimeUnit.MINUTES));
		exp.start();
		event.eventRoutine(model.getProcessContainer(), process);
		assertEquals(60, process.getStartTime(), 0);
		assertEquals(0, process.getCompletionTime(), 0);
		exp.finish();
	}

}
