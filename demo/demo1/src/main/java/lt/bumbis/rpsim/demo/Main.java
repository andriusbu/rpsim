package lt.bumbis.rpsim.demo;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.droolsjbpm.ProcessEngineImpl;

import org.drools.io.ResourceFactory;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class Main {
	
	private static final String CHANGE_SET = "changeSet.xml";
	private static final String SIM_NAME = "Simulation Experiment";

	public static void main(String[] args) {
		SimConfig conf = new SimulationConfiguration();
		conf.configure();
		SimModel model = new SimModel(conf);
		
		ProcessEngineImpl procEngine = new ProcessEngineImpl(model);
		procEngine.addChangeSet(ResourceFactory.newClassPathResource(Main.CHANGE_SET));
		procEngine.setEnableRules(true);
		model.setProcessEngine(procEngine);
		
		Experiment exp = new Experiment(Main.SIM_NAME,true);		
		model.connectToExperiment(exp);
		exp.setShowProgressBar(true);
	
		exp.stop(new TimeInstant(365*2, TimeUnit.DAYS));
		exp.start();
		exp.report();
		exp.finish();
	}
}
