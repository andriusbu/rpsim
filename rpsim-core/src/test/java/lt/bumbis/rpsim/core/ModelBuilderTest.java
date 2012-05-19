package lt.bumbis.rpsim.core;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.entities.SvcProcessor;
import lt.bumbis.rpsim.core.entities.SvcProcessorExec;
import lt.bumbis.rpsim.core.simconfig.Activity;
import lt.bumbis.rpsim.core.simconfig.DataProvider;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ResourcePool;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TokenGenerator;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.dist.ContDistNormal;
import desmoj.core.simulator.Experiment;

public class ModelBuilderTest {
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;

	@Before
	public void setUp() throws Exception {
		conf = new SimConfig() {
			public void configure() {
				name("TestModel").showInReport(false).showInTrace(false);
				add(new Activity().name("Activity1").svcProcessor("SvcProc1"));
				add(new Activity().name("Activity2").svcProcessor("SvcProc1"));
				add(new TokenGenerator()
					.name("TG1").process("Process").dist("Dist1").timeUnit(TimeUnit.MINUTES)
					.showInReport(false).showInTrace(false));
				add(new ServiceProcessor()
					.name("SvcProc1").numExec(3).dist("Dist2")
					.timeUnit(TimeUnit.MINUTES).showInReport(false).showInTrace(false));
				add(new Distribution()
					.name("Dist1").distClass(ContDistNormal.class).distParams(3.0, 3.0)
					.showInReport(false).showInTrace(false));
				add(new Distribution()
					.name("Dist2").distClass(TestDist.class).distParams(3)
					.showInReport(false).showInTrace(false));
			}
		};
		conf.configure();
		model = new SimModel(conf);
		exp = new Experiment("TestExperiment",false);
		exp.setShowProgressBar(false);
	}

	@Test
	public void testInit() {
		model.connectToExperiment(exp);
		//Check number of objects in a model
		assertEquals("Numer of reportable object not match", 10, model.getReportables().size());
		assertEquals(5, model.getEntities(true).size());
		//Check Distributions
		assertEquals(model.getDist("Dist1"), model.getReportables().get(0));
		assertEquals(model.getDist("Dist2"), model.getReportables().get(1));
		assertEquals("Dist1", model.getReportables().get(0).getName());
		assertEquals("Dist2", model.getReportables().get(1).getName());
		assertEquals(ContDistNormal.class, model.getDist("Dist1").getClass());
		assertEquals(TestDist.class, model.getDist("Dist2").getClass());
		//Check Queues
		assertEquals("SvcProc1_WQ", model.getReportables().get(5).getName());
		assertEquals("SvcProc1_IQ", model.getReportables().get(6).getName());
		//Check Entities
		int numSvcProc = 0;
		int indexSvcProc = -1;
		int numSvcProcExec = 0;
		for (int i=0;i<4;i++) {
			if (model.getEntities(false).get(i).getClass().equals(SvcProcessorExec.class))
				numSvcProcExec++;
			else if (model.getEntities(false).get(i).getClass().equals(SvcProcessor.class)) {
				numSvcProc++;
				indexSvcProc = i;
			}
		}
		assertEquals(1, numSvcProc);
		assertEquals(3, numSvcProcExec);
		//Check SvcProcessor settings
		SvcProcessor svcProc = (SvcProcessor) model.getEntities(false).get(indexSvcProc);
		assertEquals("1.", model.getReportables().get(5), svcProc.getWaitQueue());
		assertEquals("2.", model.getReportables().get(6), svcProc.getIdleQueue());
		assertEquals(3, svcProc.getIdleQueue().getQueueList().size());
		assertEquals(model.getDist("Dist2"), svcProc.getServiceTimeDist());
		assertEquals(TimeUnit.MINUTES, svcProc.getServiceTimeUnit());
		//Check token generator
		assertEquals("TG1#1", model.getTokenGenerator("TG1").getName());
		assertEquals("Process", model.getTokenGenerator("TG1").getProcessName());
		assertEquals("3.", model.getDist("Dist1"), model.getTokenGenerator("TG1").getDist());
		assertEquals(TimeUnit.MINUTES, model.getTokenGenerator("TG1").getTimeUnit());
		//Check activities
		assertEquals("4.", model.getSvcProcessor("SvcProc1"), model.getActivity("Activity1"));
		assertEquals("5.", model.getSvcProcessor("SvcProc1"), model.getActivity("Activity2"));
	}
	
	@Test
	public void testInit_resourcePool() {
		conf = new SimConfig() {
			public void configure() {
				name("TestModel").showInReport(false).showInTrace(false);
				add(new ResourcePool("ResPool1", 3, 2.0, TimeUnit.HOURS, false, false));
				add(new ResourcePool("ResPool2", 2, 1.0, TimeUnit.HOURS, false, false));
			}
		};
		conf.configure();
		model = new SimModel(conf);
		exp = new Experiment("TestExperiment",false);
		exp.setShowProgressBar(false);
		model.connectToExperiment(exp);
		assertEquals(3, model.getResourcePool("ResPool1").getResQueue().length());
		assertEquals(2, model.getResourcePool("ResPool2").getResQueue().length());
	}
	
	@Test
	public void testInit_dataProvider() {
		SimConfig conf = new SimConfig() {
			public void configure() {
				name("TestModel").showInReport(false).showInTrace(false);
				add(new DataProvider().name("DataProvider1").providerClass(TestDataProvider.class));
				add(new DataProvider().name("DataProvider2").providerClass(TestDataProvider.class));				
			}
		};
		conf.configure();
		SimModel model = new SimModel(conf);
		Experiment exp = new Experiment("TestExperiment",false);
		exp.setShowProgressBar(false);
		model.connectToExperiment(exp);
		assertEquals(2, model.getDataProviders().size());
		assertEquals(TestDataProvider.class, model.getDataProvider("DataProvider1").getClass());
	}
	
	@Test
	public void testInit_TokenGenerator() {
		SimConfig conf = new SimConfig() {
			public void configure() {
				add(new DataProvider().name("DataProvider1").providerClass(TestDataProvider.class));
				add(new TokenGenerator().name("TG1").process("Process").dist("Dist1").timeUnit(TimeUnit.MINUTES).dataProvider("DataProvider1").showInReport(false).showInTrace(false));
				add(new Distribution().name("Dist1").distClass(ContDistNormal.class).distParams(3.0, 3.0).showInReport(false).showInTrace(false));	
			}
		};
		conf.configure();
		SimModel model = new SimModel(conf);
		Experiment exp = new Experiment("TestExperiment",false);
		exp.setShowProgressBar(false);
		model.connectToExperiment(exp);
		assertEquals(TestDataProvider.class, model.getDataProvider("DataProvider1").getClass());
		assertEquals(ContDistNormal.class, model.getDist("Dist1").getClass());
	}

	@Test 
	public void testDoInitialSchedules() {
		model.connectToExperiment(exp);
		ModelBuilder.doInitialSchedules(model);
		assertTrue(model.getTokenGenerator("TG1").isScheduled());
	}
}
