package lt.bumbis.rpsim.core.simconfig;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ServiceProcessorTest {

	@Test
	public void test() {
		ServiceProcessor svcProc = new ServiceProcessor();
		svcProc.name("Test svcProc").dist("Test Dist").timeUnit(TimeUnit.MINUTES).showInReport(false).showInTrace(false);
		assertEquals("Test Dist", svcProc.getDistName());
		assertEquals(TimeUnit.MINUTES, svcProc.getTimeUnit());
	}

}
