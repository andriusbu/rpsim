package lt.bumbis.rpsim.droolsjbpm;

import java.util.concurrent.TimeUnit;
import org.drools.io.ResourceFactory;
import org.drools.runtime.conf.ClockTypeOption;
import org.jbpm.test.JbpmJUnitTestCase;
import org.junit.Test;

public class ProcessEngineImplTest extends JbpmJUnitTestCase {

	@Test
	public void testAddChangeSet() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet1.xml"));
		assertEquals(1, engine.newKnowledgeBase().getProcesses().size());
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		assertEquals(3, engine.newKnowledgeBase().getProcesses().size());
	}
	
	@Test
	public void testStartEngine() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet1.xml"));
		assertNotNull("Engine not started - Knowledge session is Null", ((ProcessEngineImpl)engine.startEngine()).getKnowledgeSession());
		assertEquals("pseudo", ((ProcessEngineImpl)engine.startEngine()).getKnowledgeSession().getSessionConfiguration().getOption(ClockTypeOption.class).getClockType());
	}
	
	@Test
	public void testStartProcess() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		engine.startEngine();
		long processId = engine.startProcess("changeSet2_process1");
		assertProcessInstanceActive(processId, engine.getKnowledgeSession());
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertProcessInstanceActive(processId, engine.getKnowledgeSession());
	}
	
//	@Test
//	public void testSetAdvanceTime() {
//		ProcessEngineImpl engine = new ProcessEngineImpl();
//		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
//		engine.startEngine();
//		long processId = engine.startProcess("changeSet2_process1");
//		assertProcessInstanceActive(processId, engine.getKnowledgeSession());
//		engine.setAdvanceTime(2, TimeUnit.MILLISECONDS);
//		engine.setAdvanceTime(3, TimeUnit.MILLISECONDS);
//		assertProcessInstanceCompleted(processId, engine.getKnowledgeSession());	
//	}
	
	@Test
	public void testSyncTime1() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		engine.startEngine();
		long processId = engine.startProcess("changeSet2_process1");
		engine.syncTime(3, TimeUnit.MILLISECONDS);
		engine.syncTime(4, TimeUnit.MILLISECONDS);
		assertProcessInstanceActive(processId, engine.getKnowledgeSession());
		engine.syncTime(5, TimeUnit.MILLISECONDS);
		assertProcessInstanceCompleted(processId, engine.getKnowledgeSession());	
	}
	
	@Test
	public void testSyncTime2() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		engine.startEngine();
		long processId = engine.startProcess("changeSet2_process1");
		engine.syncTime(1, TimeUnit.MINUTES);
		assertProcessInstanceCompleted(processId, engine.getKnowledgeSession());	
	}
	
	@Test
	public void testHandlerAndEvents() {
		TestSimEngine simEngine = new TestSimEngine(true);
		ProcessEngineImpl procEngine = new ProcessEngineImpl(simEngine);
		procEngine.addChangeSet(ResourceFactory.newClassPathResource("changeSet3.xml"));
		procEngine.startEngine();
		long processId = procEngine.startProcess("changeSet3_process1");
		assertProcessInstanceCompleted(processId, procEngine.getKnowledgeSession());
		assertEquals(1, simEngine.getNewProcessArrivalCounter());
		assertEquals(1, simEngine.getNewProcessCompleteionCounter());
		assertEquals(1, simEngine.getNewServiceRequestCounter());
	}
	
	
}