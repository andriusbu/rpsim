package lt.bumbis.rpsim.test;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.Activity;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TimerEvent;
import lt.bumbis.rpsim.core.simconfig.TokenGenerator;
import lt.bumbis.rpsim.droolsjbpm.ProcessEngineImpl;

import org.drools.io.ResourceFactory;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class Simulation2 {
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;
	
	public static void main(String[] args) {
		Simulation2 sim = new Simulation2();
		sim.test();
	}


	public void test() {
//		conf = new SimConfig()
//			.name("Simulation1 Model")
//			.add(new TokenGenerator().name("TG1").process("lt.bumbis.test").dist("DistArrival").timeUnit(TimeUnit.MINUTES))
//			.add(new Distribution().name("DistArrival").distClass(ContDistExponential.class).distParams(3.0));
//		
//		model = new SimModel(conf);
		
		ProcessEngineImpl procEngine = new ProcessEngineImpl();
		procEngine.addChangeSet(ResourceFactory.newUrlResource("http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/lt.bumbis/LATEST/ChangeSet.xml"));
//		model.setProcessEngine(procEngine);
//		
//		exp = new Experiment("Simulatio1 Experiment",true);
//		model.connectToExperiment(exp);
//		exp.setShowProgressBar(true);
//	
//		exp.stop(new TimeInstant(1, TimeUnit.DAYS));
//		exp.start();
//		exp.report();
//		System.out.println(exp.getReferenceUnit());
//		exp.finish();
	}
}
