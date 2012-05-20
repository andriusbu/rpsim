package lt.bumbis.rpsim.models.sim2;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.droolsjbpm.ProcessEngineImpl;
import lt.bumbis.rpsim.models.sim1.Configuration;

import org.drools.io.ResourceFactory;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class ModelTest1 {

	@Test
	public void test() {
		SimConfig conf = new Configuration();
		conf.configure();
		SimModel model = new SimModel(conf);
		
		ProcessEngineImpl procEngine = new ProcessEngineImpl(model);
		procEngine.addChangeSet(ResourceFactory.newClassPathResource("changeSet3.xml"));
		model.setProcessEngine(procEngine);
		
		Experiment exp = new Experiment("Simulatio1 Experiment",true);
		model.connectToExperiment(exp);
		exp.setShowProgressBar(true);
	
		exp.stop(new TimeInstant(30, TimeUnit.DAYS));
		exp.start();
		exp.report();
		exp.finish();
	}

}
