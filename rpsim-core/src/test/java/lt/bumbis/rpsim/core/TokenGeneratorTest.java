package lt.bumbis.rpsim.core;

import static org.junit.Assert.*;

import java.util.Observable;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;
import desmoj.core.simulator.TimeSpan;

public class TokenGeneratorTest {
	
	private SimulationEngine simEngine;
	private TokenGenerator tokenGenerator;
	private String procEngineStartProcessCalled;
	private long procEngineStartProcessCalledNum;
	private Observable observable;
	
	@Before
	public void before() {
		procEngineStartProcessCalled = "";
		procEngineStartProcessCalledNum = 0;
		observable = null;
		simEngine = new SimulationEngine(null, "Test Model", true, false);
		simEngine.setProcessEngine(new ProcessEngine() {
			public ProcessEngine startEngine() {
				return null;
			}
			public long startProcess(String processName) {
				procEngineStartProcessCalled = processName;
				procEngineStartProcessCalledNum++;
				return 1;
			}
			public void update(Observable o, Object arg) {
				observable = o;
				
			}			
		});
		simEngine.setModelBuilder(new ModelBuilder(simEngine) {
			@Override
			public void init() {
				
			}
			@Override
			public void doInitialSchedules() {
				ContDistExponential dist = new ContDistExponential(simEngine, "Token Generator Dist", 3.0, true, false);
				tokenGenerator = new TokenGenerator(simEngine, "Test Token Generator", true);
				tokenGenerator.settings("process1", dist, TimeUnit.MINUTES);
				tokenGenerator.schedule(new TimeSpan(0));
			}
		});
	}
	
	@Test
	public void testEventRoutine () {
		Experiment exp = new Experiment("Test Experiment", false);
		exp.setShowProgressBar(false);
		simEngine.connectToExperiment(exp);
		exp.stop(new TimeInstant(1, TimeUnit.MILLISECONDS));
		exp.start();
		assertNotNull("Observer not attached to SimClock", observable);
		assertEquals("Token generator did not call startProcess() method", "process1", procEngineStartProcessCalled);
		exp.stop(new TimeInstant(10, TimeUnit.MINUTES));
		exp.start();
		assertEquals("Tokent generator did not scheduled it self", 4, procEngineStartProcessCalledNum);
		exp.finish();
	}
	

}
