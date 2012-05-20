package lt.bumbis.rpsim.models.sim1;

import java.util.concurrent.TimeUnit;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.dist.ContDistUniform;
import lt.bumbis.rpsim.core.simconfig.Activity;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TimerEvent;
import lt.bumbis.rpsim.core.simconfig.TokenGenerator;

public class Configuration extends SimConfig {

	@Override
	public void configure() {
		name("Simulation1 Model")
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
	}
}
