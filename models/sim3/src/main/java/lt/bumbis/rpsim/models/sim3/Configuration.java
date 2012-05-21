package lt.bumbis.rpsim.models.sim3;

import java.util.concurrent.TimeUnit;

import desmoj.core.dist.ContDistExponential;
import lt.bumbis.rpsim.core.simconfig.DataProvider;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TokenGenerator;

public class Configuration extends SimConfig {

	@Override
	public void configure() {
		name("Simulation1 Model");
		add(new TokenGenerator().name("TG1").process("lt.bumbis.rpsim.models.sim3.process1").dist("DistArrival").timeUnit(TimeUnit.MINUTES).dataProvider("DataProvider"));
		add(new DataProvider().name("DataProvider").providerClass(DataProviderImpl.class));
		add(new Distribution().name("DistArrival").distClass(ContDistExponential.class).distParams(3.0));
	}
}
