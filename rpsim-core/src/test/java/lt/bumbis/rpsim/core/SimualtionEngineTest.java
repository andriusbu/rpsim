package lt.bumbis.rpsim.core;

import java.util.Observable;
import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimulationEngine;
import org.jbpm.test.JbpmJUnitTestCase;
import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.SimClock;
import desmoj.core.simulator.TimeInstant;

public class SimualtionEngineTest extends JbpmJUnitTestCase {
	
	private SimulationEngine simEngine;
	private Experiment exp; 
	private boolean procEngineStarted;
	private boolean modelBuilderInitCalled;
	private boolean modelBuilderDoInitShedulesCalled;
	private Observable observable;
	
	@Before
	public void before() {
		simEngine = new SimulationEngine(null, "Test Model", true, false);
		procEngineStarted = false;
		modelBuilderInitCalled = false;
		modelBuilderDoInitShedulesCalled = false;
		simEngine.setProcessEngine(new ProcessEngine() {
			public ProcessEngine startEngine() {
				procEngineStarted = true;
				return null;
			}
			public long startProcess(String processName) {
				return 0;
			}
			public void setAdvanceTime(long amount, TimeUnit unit) {
			}
			public void setTime(long amount) {
			}
			public void update(Observable o, Object arg) {
				observable = o;
			}			
		});
		simEngine.setModelBuilder(new ModelBuilder(simEngine) {

			@Override
			public void init() {
				modelBuilderInitCalled = true;
			}

			@Override
			public void doInitialSchedules() {
				modelBuilderDoInitShedulesCalled = true;
			}
			
		});
		exp = new Experiment("Test Experiment", false);
		exp.setShowProgressBar(false);
		simEngine.connectToExperiment(exp);
	}

	@Test
	public void testInit() {
		simEngine.init();
		assertTrue("ProcessEngine not started", procEngineStarted);
		assertTrue("Model builder init() method not called", modelBuilderInitCalled);
	}
	
	@Test
	public void testDoInitialSchedules1() {
		simEngine.doInitialSchedules();
		assertTrue("Model builder doInitialSchedules() mothod not called", modelBuilderDoInitShedulesCalled);
	}
	
	@Test
	public void testDoInitialSchedules2() {
		exp.stop(new TimeInstant(1, TimeUnit.MILLISECONDS));
		exp.start();
		assertNotNull("Observer not attached to SimClock", observable);
		assertEquals("Wrong observer", observable.getClass(), SimClock.class);
	}
	
	
}
