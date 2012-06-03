package lt.bumbis.rpsim.demo;

import java.util.concurrent.TimeUnit;

import desmoj.core.dist.ContDistExponential;

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
				.dataProvider("DP"));
		add(new Distribution().name("DistArrival")
				.distClass(ContDistExponential.class).distParams(3.0));
		add(new DataProvider().name("DP").providerClass(DataProviderImpl.class));
		add(new ServiceProcessor().name("Email1"));
	}
}