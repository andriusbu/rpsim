package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimulationEngine;
import org.jbpm.test.JbpmJUnitTestCase;
import org.junit.Before;
import org.junit.Test;

public class SimualtionEngineTest extends JbpmJUnitTestCase {
	
	private SimulationEngine simEngine;
	private boolean procEngineStarted;
	private boolean modelBuilderInitCalled;
	private boolean modelBuilderDoInitShedulesCalled;
	
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
	}

	@Test
	public void testInit() {
		simEngine.init();
		assertTrue("ProcessEngine not started", procEngineStarted);
		assertTrue("Model builder init() method not called", modelBuilderInitCalled);
	}
	
	@Test
	public void testDoInitialSchedules() {
		simEngine.doInitialSchedules();
		assertTrue("Model builder doInitialSchedules() mothod not called", modelBuilderDoInitShedulesCalled);
	}
	
	
}
