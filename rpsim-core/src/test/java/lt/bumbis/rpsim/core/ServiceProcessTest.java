package lt.bumbis.rpsim.core;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Model;

public class ServiceProcessTest {

	@Test
	public void testInit() {
		SimModel model = new SimModel(null, "Test", false, false);
		Experiment exp = new Experiment("Test Experiment", false);
		model.connectToExperiment(exp);
		ServiceProcessor svcProc = new ServiceProcessor(
				"TestSvcProc",
				1,
				TestDist.class,
				TimeUnit.MINUTES,
				false
		);
		svcProc.init(model);
	}
	
	private class TestDist extends ContDist {

		public TestDist(Model arg0, String arg1, boolean arg2, boolean arg3) {
			super(arg0, arg1, arg2, arg3);
		}

		@Override
		public Double sample() {
			return 33.0;
		}
		
	}

}
