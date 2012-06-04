package lt.bumbis.rpsim.droolsjbpm;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.drools.runtime.conf.ClockTypeOption;
import org.jbpm.test.JbpmJUnitTestCase;
import org.junit.Before;
import org.junit.Test;

public class ProcessEngineGuvnorTest1 extends JbpmJUnitTestCase {

	private static String process1 = "lt.bumbis.rpsim.testProcess1";
	private ProcessEngineGuvnor engine;

	@Before
	public void setUp() throws Exception {
		engine = new ProcessEngineGuvnor();
//		engine.addLoaderURLs(new URL("http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/lt.bumbis.rpsim/LATEST/MODEL"));
//		engine.addLoaderURLs(new URL("http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/lt.bumbis.rpsim/LATEST"));
		engine.addLoaderURLs(new URL("file://"));
		engine.addPackage("lt.bumbis.rpsim.pkg");
	}

	@Test
	public void testNewKnowledgeBase() {
		assertEquals(1, engine.newKnowledgeBase().getProcesses().size());
	}
	
	@Test
	public void testStartEngine() {
		assertNotNull("Engine not started - Knowledge session is Null", engine.startEngine().getKnowledgeSession());
		assertEquals("pseudo", engine.startEngine().getKnowledgeSession().getSessionConfiguration().getOption(ClockTypeOption.class).getClockType());
	}
	
	@Test
	public void testStartProcess1() {
		engine.startEngine();
		long processId = engine.startProcess(process1);
		assertProcessInstanceActive(processId, engine.getKnowledgeSession());
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertProcessInstanceActive(processId, engine.getKnowledgeSession());
	}
	
	@Test
	public void testStartProcess2() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("Var1", "Value1");
		engine.startEngine();
		long processId = engine.startProcess(process1, data);
		assertProcessInstanceActive(processId, engine.getKnowledgeSession());
		assertProcessVarExists(engine.getKnowledgeSession().getProcessInstance(processId), "Var1");
	}
}