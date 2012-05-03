package lt.bumbis.rpsim.core.simconfig;

import static org.junit.Assert.*;
import org.junit.Test;

public class SimConfigTest {

	@Test
	public void testAddGet() {
		SimConfig conf = new SimConfig("Test");
		conf
			.add(new Distribution("Dist1"))
			.add(new Distribution("Dist2"))
			.add(new TokenGenerator("TG1"))
			.add(new ServiceProcessor("SvcProc1"));
		assertEquals("Step1", 2, conf.get(Distribution.class).size());
		assertTrue("Step2", conf.get(Distribution.class).containsKey("Dist2"));
		assertEquals("Step3", 1, conf.get(TokenGenerator.class).size());
		assertTrue("Step4", conf.get(TokenGenerator.class).containsKey("TG1"));
		assertEquals("Step5", 1, conf.get(ServiceProcessor.class).size());
		assertTrue("Step6", conf.get(ServiceProcessor.class).containsKey("SvcProc1"));
	}

}
