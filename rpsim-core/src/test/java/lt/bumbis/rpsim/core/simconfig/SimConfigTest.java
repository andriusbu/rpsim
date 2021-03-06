package lt.bumbis.rpsim.core.simconfig;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.TestDataProvider;

import org.junit.Test;

public class SimConfigTest {

	@Test
	public void testAddGet() {
		SimConfig conf = new SimConfig() {
				public void configure() {
					name("TestModel").showInReport(false).showInTrace(false)
					.add(new Distribution("Dist1", null, null, false, false))
					.add(new Distribution("Dist2", null, null, false, false))
					.add(new TokenGenerator("TG1", null, null, null, false, false))
					.add(new ServiceProcessor("SvcProc1", 1, null, null, false, false))
					.add(new Activity("Activity1", "SvcProc1"))
					.add(new Activity("Activity2", "SvcProc1"))
					.add(new ResourcePool("ResPool1", 100, 10.0, TimeUnit.HOURS, false, false));
				}
			};
		conf.configure();
		assertEquals("Step1", 2, conf.getDists().size());
		assertTrue("Step2", conf.getDists().containsKey("Dist2"));
		assertEquals("Step3", 1, conf.getTokenGens().size());
		assertTrue("Step4", conf.getTokenGens().containsKey("TG1"));
		assertEquals("Step5", 1, conf.getSvcProcs().size());
		assertTrue("Step6", conf.getSvcProcs().containsKey("SvcProc1"));
		assertEquals("Step7", 2, conf.getActivities().size());
		assertTrue("Step8", conf.getActivities().containsKey("Activity1"));
		assertEquals("Step9", 1, conf.getResourcePools().size());
		assertNotNull("Step10", conf.getResourcePool("ResPool1"));
	}
	
	@Test
	public void testTimerEvent() {
		SimConfig conf = new SimConfig() {
			public void configure() {
				name("TestModel").showInReport(false).showInTrace(false);
				add(new TimerEvent("Timer1", 10, TimeUnit.MINUTES, false, false));
				add(new TimerEvent("Timer2", 1, TimeUnit.SECONDS, false, false));
			}
		};
		conf.configure();		
		assertEquals(2, conf.getTimerEvents().size());
		assertEquals(10, conf.getTimerEvent("Timer1").getTime());
	}
	
	@Test
	public void testDataProvider() {
		SimConfig conf = new SimConfig() {
			public void configure() {
				name("TestModel").showInReport(false).showInTrace(false);
				add(new DataProvider().name("Data Provider1").providerClass(TestDataProvider.class));
				add(new DataProvider().name("Data Provider2").providerClass(TestDataProvider.class));
			}
		};
		conf.configure();
		assertEquals(2, conf.getDataProviders().size());
		assertEquals(TestDataProvider.class, conf.getDataProvider("Data Provider1").getProvider());
	}
}
