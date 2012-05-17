package lt.bumbis.rpsim.core.simconfig;

import static org.junit.Assert.*;
import lt.bumbis.rpsim.core.TestDataProvider;

import org.junit.Test;

public class DataProviderTest {

	@Test
	public void testProviderClass() {
		DataProvider provider = new DataProvider();
		provider.name("Provider1").providerClass(TestDataProvider.class);
		assertEquals("Provider1", provider.getName());
		assertEquals(TestDataProvider.class, provider.getProvider());
	}
}
