package lt.bumbis.rpsim.models;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.droolsjbpm.ProcessEngineImpl;

import org.drools.io.ResourceFactory;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class Sim1Test {

	@Test
	public void test() {
		SimConfig conf = new Sim1();
		conf.configure();
		SimModel model = new SimModel(conf);
		
		ProcessEngineImpl procEngine = new ProcessEngineImpl(model);
		procEngine.addChangeSet(ResourceFactory.newClassPathResource("changeSet3.xml"));
		model.setProcessEngine(procEngine);
		
		Experiment exp = new Experiment("Simulatio1 Experiment",true);
		model.connectToExperiment(exp);
		exp.setShowProgressBar(true);
	
		exp.stop(new TimeInstant(365, TimeUnit.DAYS));
		exp.start();
		exp.report();
		exp.finish();
	}

}
