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

public class Simulation1 {
	
	private SimConfig conf;
	private SimModel model;
	private Experiment exp;
	
	public static void main(String[] args) {
		Simulation1 sim = new Simulation1();
		sim.test();
	}


	public void test() {
		conf = new SimConfig()
			.name("Simulation1 Model")
			.add(new TokenGenerator().name("TG1").process("changeSet3_process1").dist("DistArrival").timeUnit(TimeUnit.MINUTES))
			.add(new ServiceProcessor().name("SvcProc Test").numExec(3).dist("DistService").timeUnit(TimeUnit.MINUTES))
			.add(new ServiceProcessor().name("SvcProc Human Task").numExec(5).dist("DistService").timeUnit(TimeUnit.MINUTES))
			.add(new ServiceProcessor().name("SvcProc User Task").numExec(5).dist("DistService").timeUnit(TimeUnit.MINUTES))
			.add(new Distribution().name("DistArrival").distClass(ContDistExponential.class).distParams(3.0))
			.add(new Distribution().name("DistService").distClass(ContDistUniform.class).distParams(3.0, 7.0))
			.add(new Activity().name("1").svcProcessor("SvcProc Test"))
			.add(new Activity().name("3").svcProcessor("SvcProc Human Task"))
			.add(new Activity().name("4").svcProcessor("SvcProc User Task"))
			.add(new TimerEvent().name("Catch").time(5).timeUnit(TimeUnit.MINUTES));
		
		model = new SimModel(conf);
		
		ProcessEngineImpl procEngine = new ProcessEngineImpl(model);
		procEngine.addChangeSet(ResourceFactory.newClassPathResource("changeSet3.xml"));
		model.setProcessEngine(procEngine);
		
		exp = new Experiment("Simulatio1 Experiment",true);
		model.connectToExperiment(exp);
		exp.setShowProgressBar(true);
	
		exp.stop(new TimeInstant(365, TimeUnit.DAYS));
		exp.start();
		exp.report();
		System.out.println(exp.getReferenceUnit());
		exp.finish();
	}
}
