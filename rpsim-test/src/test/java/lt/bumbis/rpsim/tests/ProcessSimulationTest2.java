package lt.bumbis.rpsim.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.DataProvider;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TokenGenerator;
import lt.bumbis.rpsim.droolsjbpm.ProcessEngineGuvnor;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class ProcessSimulationTest2 {

	private static String process1 = "lt.bumbis.rpsim2.testProcess1";
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;
	private ProcessEngineGuvnor procEngine;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() throws MalformedURLException {
		conf = new SimConfig("TestModel", false, false);
		conf
			.add(new TokenGenerator("TG1", process1, "DistArrival", TimeUnit.MINUTES, false, false).dataProvider("DataProvider"))
			.add(new ServiceProcessor("SvcProc1", 5, "DistService", TimeUnit.MINUTES, false, false))
			.add(new Distribution("DistArrival", ContDistExponential.class, new Object[] {3.0}, false, false))
			.add(new Distribution("DistService", ContDistUniform.class, new Object[] {3.0, 7.0}, false, false))
			.add(new DataProvider().name("DataProvider").providerClass(TestDataProvider.class));
		
		model = new SimModel(conf);
		
		procEngine = new ProcessEngineGuvnor();
		procEngine.addLoaderURLs(new URL("http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/lt.bumbis.rpsim2/LATEST"));
		procEngine.addLoaderURLs(new URL("http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/lt.bumbis.rpsim2/LATEST/MODEL"));
		procEngine.addPackage(new URL("http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/lt.bumbis.rpsim2/LATEST"));
		
		model.setProcessEngine(procEngine);
		
		exp = new Experiment("TestExperiment",false);
		model.connectToExperiment(exp);
		exp.setShowProgressBar(false);
		exp.setSilent(true);
		
		exp.stop(new TimeInstant(10, TimeUnit.MINUTES));
		exp.start();
		exp.report();
		exp.finish();
	}
}
