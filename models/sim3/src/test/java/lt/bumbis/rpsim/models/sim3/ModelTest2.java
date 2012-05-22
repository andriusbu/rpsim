package lt.bumbis.rpsim.models.sim3;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.droolsjbpm.ProcessEngineGuvnor;
import lt.bumbis.rpsim.droolsjbpm.ProcessEngineImpl;
import lt.bumbis.rpsim.models.sim3.Configuration;

import org.drools.io.ResourceFactory;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class ModelTest2 {

	@Test
	public void test2() {
		SimConfig conf = new Configuration();
		conf.configure();
		SimModel model = new SimModel(conf);

		ProcessEngineImpl procEngine = new ProcessEngineImpl(model);
		procEngine.addChangeSet(ResourceFactory.newClassPathResource("changeSet.xml"));
		procEngine.setEnableRules(true);
		model.setProcessEngine(procEngine);
		
		Experiment exp = new Experiment("Simulation Experiment",true);
		model.connectToExperiment(exp);
		exp.setShowProgressBar(true);
	
		exp.stop(new TimeInstant(1, TimeUnit.DAYS));
		exp.start();
		exp.report();
		exp.finish();
	}
}
