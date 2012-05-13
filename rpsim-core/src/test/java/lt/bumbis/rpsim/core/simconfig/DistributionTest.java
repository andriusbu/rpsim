package lt.bumbis.rpsim.core.simconfig;

import static org.junit.Assert.*;
import lt.bumbis.rpsim.core.TestDist;

import org.junit.Test;

public class DistributionTest {

	@Test
	public void test() {
		Distribution dist = new Distribution();
		dist.name("TestDist").distClass(TestDist.class).distParams(1).showInReport(false).showInTrace(false);
		assertEquals("TestDist", dist.getName());
		assertEquals(TestDist.class, dist.getDistClass());
		assertEquals(1, dist.getDistParams().length);
	}
}
