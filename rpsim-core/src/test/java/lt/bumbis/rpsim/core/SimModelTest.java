package lt.bumbis.rpsim.core;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.elements.Distribution;
import lt.bumbis.rpsim.core.elements.ServiceProcessor;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;

public class SimModelTest {

	private SimModel model;
	private Experiment exp;
	
	@Before
	public void setUp() throws Exception {
		model = new SimModel(null, "Test", true, false);		
		exp = new Experiment("Test Experiment", false);
	}

	@Test
	public void testDoInitialSchedules() {
		HashMap<String, TokenGenerator> tGens = new HashMap<String, TokenGenerator>();
		tGens.put("TG1", new TestTokenGenerator("TG1"));
		tGens.put("TG2", new TestTokenGenerator("TG1"));
		model.setTokenGenerators(tGens);
		model.setTimeSyncHandler(new TestProcessEngine());
		model.connectToExperiment(exp);
		exp.start();
		assertEquals(3, TestTokenGenerator.schedulesCalled);
		assertEquals(0, TestProcessEngine.updateCalled);
	}

	@Test
	public void testInit_SvcProcessors() {
		HashMap<String, ServiceProcessor> svcProcessors = new HashMap<String, ServiceProcessor>();
		svcProcessors.put("Test SP1", new TestServiceProcessor("Test SP1", "Test Dist1"));
		svcProcessors.put("Test SP2", new TestServiceProcessor("Test SP2", "Test Dist2"));
		model.setSvcProcessors(svcProcessors);
		model.connectToExperiment(exp);
		assertEquals(2, TestServiceProcessor.initCalled);
	}
	
	@Test
	public void testInit_Distributions() {
		HashMap<String, Distribution> dists = new HashMap<String, Distribution>();
		dists.put("Test Dist1", new TestDistribution("Test Dist1"));
		dists.put("Test Dist2", new TestDistribution("Test Dist2"));
		model.setDistributions(dists);
		model.connectToExperiment(exp);
		assertEquals(2, TestDistribution.initCalled);
	}
	
	@Test
	public void testInit_TokenGenerator() {
		HashMap<String, TokenGenerator> tGens = new HashMap<String, TokenGenerator>();
		tGens.put("TG1", new TestTokenGenerator("TG1"));
		tGens.put("TG2", new TestTokenGenerator("TG1"));
		model.setTokenGenerators(tGens);
		model.connectToExperiment(exp);
		assertEquals(2, TestTokenGenerator.initCalled);
	}

	
	private static class TestServiceProcessor extends ServiceProcessor {
		static public int initCalled;
		
		public TestServiceProcessor(String name, String distName) {
			super(name, 1, distName, TimeUnit.MINUTES, false, false);
			initCalled = 0;
		}
		
		@Override
		public void init(SimModel model) {
			initCalled++;
		}
	}
	
	private static class TestDistribution extends Distribution{
		static public int initCalled;

		public TestDistribution(String name) {
			super(name, null, null, false, false);
		}
		
		@Override
		public void init(SimModel model) {
			initCalled++;
		}
		
	}
	
	private static class TestTokenGenerator extends TokenGenerator {
		static public int initCalled;
		static public int schedulesCalled;

		public TestTokenGenerator(String name) {
			super(name, "Process", "Dist", TimeUnit.MINUTES, false);
			initCalled = 0;
			schedulesCalled = 0;
		}
		
		@Override
		public void init(SimModel model) {
			initCalled++;
		}
		
		@Override
		public void doInitialSchedules(SimModel model) {
			schedulesCalled++;
		}
	}
	
	private static class TestProcessEngine implements IProcessEngine, Observer {
		static public int updateCalled;
		public TestProcessEngine() {
			updateCalled = 0;
		}
		public void update(Observable o, Object arg) {
			updateCalled ++;
		}
		public ProcessEngine startEngine() {
			// TODO Auto-generated method stub
			return null;
		}
		public long startProcess(String processName) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
}
