package lt.bumbis.rpsim.core.events;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.TestDataProvider;
import lt.bumbis.rpsim.core.TestDist;
import lt.bumbis.rpsim.core.TestProcessEngine;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TokenGenerator;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class NewProcessTokenTest {
	
	private SimConfig conf;
	private Experiment exp;
	private SimModel model;
	private TestProcessEngine procEngine;
	private NewProcessToken tokenGen;

	@Before
	public void setUp() throws Exception {
		conf = new SimConfig() {
			public void configure() {
				name("TestModel").showInReport(false).showInTrace(false);
				add(new TokenGenerator("TG1", "Process", "Dist1", TimeUnit.MINUTES, false, false));
				add(new Distribution("Dist1", TestDist.class, new Object[] {}, false, false));				
			}
		};
		conf.configure();
		model = new SimModel(conf);
		procEngine = new TestProcessEngine();
		model.setProcessEngine(procEngine);
		exp = new Experiment("TestExperiment",false);
		exp.setShowProgressBar(false);
		exp.setSilent(true);
		model.connectToExperiment(exp);
		tokenGen = model.getTokenGenerator("TG1");
	}

	@Test
	public void testEventRoutine_Brahcn1() {
		tokenGen.eventRoutine();
		assertEquals(1, procEngine.getProcStartCount());
		assertEquals(0, procEngine.getProcWithDataStartCount());
		assertEquals("Process", procEngine.getLastProcessName());
	}
	
	@Test
	public void testEventRoutine_Branch2() {
		exp.stop(new TimeInstant(70, TimeUnit.MINUTES));
		exp.start();
		assertEquals(2, procEngine.getProcStartCount());
		assertEquals(0, procEngine.getProcWithDataStartCount());
		assertEquals("Process", procEngine.getLastProcessName());
		exp.finish();
	}
	
	@Test
	public void testEventRoutine_CountStats() {
		tokenGen.eventRoutine();
		assertEquals(1, tokenGen.getTokenCount().getValue());
		tokenGen.eventRoutine();
		assertEquals(2, tokenGen.getTokenCount().getValue());
	}
	
	@Test
	public void testEventRoutine_Data() {
		TestDataProvider dataProvider = new TestDataProvider();
		tokenGen.setDataProvider(dataProvider);
		tokenGen.eventRoutine();
		assertEquals(1, procEngine.getProcStartCount());
		assertEquals(1, procEngine.getProcWithDataStartCount());
		assertEquals("Process", procEngine.getLastProcessName());
		assertEquals(1, dataProvider.getPrepareDataCount());
		assertEquals(1, dataProvider.getGetProcessDataCount());
		assertEquals(0, dataProvider.getGetContextDataCount());
	}

}
