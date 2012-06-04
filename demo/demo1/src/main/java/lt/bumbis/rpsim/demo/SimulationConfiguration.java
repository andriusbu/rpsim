package lt.bumbis.rpsim.demo;

import java.util.concurrent.TimeUnit;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.dist.ContDistUniform;

import lt.bumbis.rpsim.core.simconfig.Activity;
import lt.bumbis.rpsim.core.simconfig.DataProvider;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TokenGenerator;

public class SimulationConfiguration extends SimConfig {

	@Override
	public void configure() {
		name("Demo simulation");
		add(new TokenGenerator().name("TG")
				.process("lt.bumbis.rpsim.demo.requestHandling")
				.dist("DistArrival").timeUnit(TimeUnit.MINUTES)
				.dataProvider("DP").useContextData(true));
		// Distributions
		add(new Distribution().name("DistArrival")
				.distClass(ContDistExponential.class).distParams(15.0));
		add(new Distribution().name("DistAuto")
				.distClass(ContDistUniform.class).distParams(5.0, 2.0));
		add(new Distribution().name("DistHT").distClass(ContDistUniform.class)
				.distParams(3.0, 7.0));
		add(new Distribution().name("DistHT1").distClass(ContDistUniform.class)
				.distParams(15.0, 20.0));
		// Data providers
		add(new DataProvider().name("DP").providerClass(DataProviderImpl.class));
		// Service processors
		add(new ServiceProcessor().name("Auto-Process").dist("DistAuto")
				.numExec(100).timeUnit(TimeUnit.SECONDS));
		add(new ServiceProcessor().name("Review").dist("DistHT").numExec(2)
				.timeUnit(TimeUnit.MINUTES));
		add(new ServiceProcessor().name("Finalize").dist("DistHT").numExec(1)
				.timeUnit(TimeUnit.MINUTES));
		add(new ServiceProcessor().name("Investigate&Approve").dist("DistHT")
				.numExec(1).timeUnit(TimeUnit.MINUTES));
		add(new ServiceProcessor().name("Finalize").dist("DistHT1").numExec(1)
				.timeUnit(TimeUnit.MINUTES));
		// Activity mapping
		add(new Activity().name("Review").svcProcessor("Review"));
		add(new Activity().name("Auto-Process").svcProcessor("Auto-Process"));
		add(new Activity().name("Investigate").svcProcessor(
				"Investigate&Approve"));
		add(new Activity().name("Approve").svcProcessor("Investigate&Approve"));
		add(new Activity().name("Finalize").svcProcessor("Finalize"));
	}
}