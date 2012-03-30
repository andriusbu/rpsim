package lt.bumbis.rpsim;

import static org.junit.Assert.*;

import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class SimualtionModelTest {

	@Test
	public void TestDoInitialShedules() {
		SimulationModel model = new SimulationModel(null, "Test Model", true, false);
		Experiment exp = new Experiment("Test Experiment", false);
		model.connectToExperiment(exp);
		exp.stop(new TimeInstant(0));
		exp.start();
		exp.start();
		assertEquals(TokenGenerator.class ,model.current().getClass());
		exp.finish();
	}
	
	@Test
	public void TestStartProcess() {
		fail("Not yet implemented");
	}

}
