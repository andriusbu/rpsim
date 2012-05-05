package lt.bumbis.rpsim.core.simconfig;

import static org.junit.Assert.*;
import org.junit.Test;

public class SimConfigTest {

	@Test
	public void testAddGet() {
		SimConfig conf = new SimConfig("Test", false, false);
		conf
			.add(new Distribution("Dist1", null, null, false, false))
			.add(new Distribution("Dist2", null, null, false, false))
			.add(new TokenGenerator("TG1", null, null, null, false, false))
			.add(new ServiceProcessor("SvcProc1", 1, null, null, false, false))
			.add(new Activity("Activity1", "SvcProc1"))
			.add(new Activity("Activity2", "SvcProc1"));
		assertEquals("Step1", 2, conf.getDists().size());
		assertTrue("Step2", conf.getDists().containsKey("Dist2"));
		assertEquals("Step3", 1, conf.getTokenGens().size());
		assertTrue("Step4", conf.getTokenGens().containsKey("TG1"));
		assertEquals("Step5", 1, conf.getSvcProcs().size());
		assertTrue("Step6", conf.getSvcProcs().containsKey("SvcProc1"));
		assertEquals("Step7", 2, conf.getActivities().size());
		assertTrue("Step8", conf.getActivities().containsKey("Activity1"));
	}

}
