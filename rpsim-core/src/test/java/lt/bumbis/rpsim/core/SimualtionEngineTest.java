package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimulationEngine;
import org.jbpm.test.JbpmJUnitTestCase;
import org.junit.Before;
import org.junit.Test;

public class SimualtionEngineTest extends JbpmJUnitTestCase {
	
	private SimulationEngine model;
	private ProcessEngine engine;
	private boolean engineStarted;
	
	@Before
	public void before() {
		model = new SimulationEngine(null, "Test Model", true, false);
		engineStarted = false;
		engine = new ProcessEngine() {
			public ProcessEngine startEngine() {
				engineStarted = true;
				return null;
			}
			public long startProcess(String processName) {
				return 0;
			}
			public void setAdvanceTime(long amount, TimeUnit unit) {
	
			}
			public void setTime(long amount) {
			
			}			
		};
		model.setProcessEngine(engine);
	}

	@Test
	public void testInit() {
		model.init();
		assertTrue("ProcessEngine not started", engineStarted);
	}
}
