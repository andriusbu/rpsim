package lt.bumbis.rpsim.core.simconfig;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActivityTest {

	@Test
	public void test() {
		Activity activity = new Activity();
		activity.name("TestActivity").svcProcessor("TestProcessor").showInReport(false).showInTrace(false);
		assertEquals("TestActivity", activity.getName());
		assertEquals("TestProcessor", activity.getSvcProcessor());
	}

}
