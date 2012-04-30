package lt.bumbis.rpsim.core;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import antlr.Token;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Experiment;

public class TokenGeneratorTest {
	
	private SimModel model;
	private Experiment exp;
	private TokenGenerator tGen;
	private HashMap<String, TokenGenerator> tGens;
	private HashMap<String, Distribution> dists;
	
	@Before
	public void doBefore() {
		model = new SimModel(null, "Test", true, false);
		tGens = new HashMap<String, TokenGenerator>();
		tGen = new TokenGenerator("Main", "Process", "Dist", TimeUnit.MINUTES);
		tGens.put(
				"TG",
				tGen
			);
		model.setTokenGenerators(tGens);
		dists = new HashMap<String, Distribution>();
		dists.put(
				"Dist",
				new Distribution("Dist", TestDist.class, new Object[] {}, true, false)
			);
		model.setDistributions(dists);
		exp = new Experiment("Test Experiment", false);
		model.connectToExperiment(exp);
	}

	@Test
	public void testInit() {
		assertEquals(tGens.get("TG").getDist(), model.getDist("Dist"));
	}
	
	@Test
	public void testDoInitialSchedules() {
		
	}
}
