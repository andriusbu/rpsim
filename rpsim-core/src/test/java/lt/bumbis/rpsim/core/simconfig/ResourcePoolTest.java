package lt.bumbis.rpsim.core.simconfig;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ResourcePoolTest {

	@Test
	public void test() {
		ResourcePool resPool = new ResourcePool();
		resPool.name("ResPool").count(100).price(10.0).tiemUnit(TimeUnit.DAYS).showInReport(false).showInTrace(false);
		assertEquals(100, resPool.getResourceCount());
	}

}
